package com.liang.p2p.business.service.impl;

import com.liang.p2p.base.domain.Account;
import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.service.IAccountService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.BidConst;
import com.liang.p2p.base.util.BitStatesUtils;
import com.liang.p2p.base.util.UserContext;
import com.liang.p2p.business.domain.BidRequest;
import com.liang.p2p.business.domain.BidRequestAuditHistory;
import com.liang.p2p.business.mapper.BidRequestAuditHistoryMapper;
import com.liang.p2p.business.mapper.BidRequestMapper;
import com.liang.p2p.business.query.BidRequestQueryObject;
import com.liang.p2p.business.service.IBidRequestService;
import com.liang.p2p.business.util.CalculatetUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liang on 2018/5/17.
 */
@Service
public class BidRequestServiceImpl implements IBidRequestService {

    @Autowired
    private BidRequestMapper bidRequestMapper;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;


    public List<BidRequest> listIndex(int size) {
        BidRequestQueryObject qo = new BidRequestQueryObject();
        qo.setBidRequestStates(new int[] { BidConst.BIDREQUEST_STATE_BIDDING,
                BidConst.BIDREQUEST_STATE_PAYING_BACK,
                BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK });
        qo.setPageSize(size);
        qo.setCurrentPage(1);
        qo.setOrderBy("bidRequestState");
        qo.setOrderType("ASC");
        return bidRequestMapper.query(qo);
    }

    public void update(BidRequest bidRequest) {
        int ret = bidRequestMapper.updateByPrimaryKey(bidRequest);
        if (ret == 0) {
            throw new RuntimeException("乐观锁失败   bidRequest:"
                    + bidRequest.getId());
        }
    }

    public BidRequest get(Long id) {
        return bidRequestMapper.selectByPrimaryKey(id);
    }

    public List<BidRequestAuditHistory> listAuditHistoryByBidRequest(Long id) {
        return bidRequestAuditHistoryMapper.listByBidRequest(id);
    }

    public PageResult query(BidRequestQueryObject qo) {
        int count = bidRequestMapper.queryForCount(qo);
        if (count > 0) {
            List<BidRequest> list = bidRequestMapper.query(qo);
            return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
        }
        return PageResult.empty(qo.getPageSize());
    }

    public boolean canApplyBidRequest(Long logininfoId) {
        // 得到指定用户
        Userinfo userinfo = userinfoService.get(logininfoId);
        // 判断1，基本资料；2.实名认证；3.视频认证；4.风控资料分数；5.没有借款在流程当中
        return (userinfo != null
                && userinfo.getIsBasicInfo()
                && userinfo.getIsRealAuth()
                && userinfo.getIsVedioAuth()
                && userinfo.getScore() >= BidConst.BASE_BORROW_SCORE
                && !userinfo.getHasBidRequestProcess());
    }

    public void apply(BidRequest br) {
        Account account = accountService.getCurrent();
        // 首先要满足最基本的申请条件
        if (this.canApplyBidRequest(UserContext.getCurrent().getId())
                && br.getBidRequestAmount().compareTo(
                BidConst.SMALLEST_BIDREQUEST_AMOUNT) >= 0// 系统最小借款金额<=借款金额
                && br.getBidRequestAmount().compareTo(
                account.getRemainBorrowLimit()) <= 0// 借款金额<=剩余信用额度
                && br.getCurrentRate()
                .compareTo(BidConst.SMALLEST_CURRENT_RATE) >= 0// 5<=利息
                && br.getCurrentRate().compareTo(BidConst.MAX_CURRENT_RATE) <= 0// 利息<=20
                && br.getMinBidAmount().compareTo(BidConst.SMALLEST_BID_AMOUNT) >= 0// 最小投标金额>=系统最小投标金额
                ) {
            //==============进入借款申请==================
            // 1. 创建一个新的Bidrequest,设置相关参数
            BidRequest bidRequest = new BidRequest();
            bidRequest.setBidRequestAmount(br.getBidRequestAmount());
            bidRequest.setCurrentRate(br.getCurrentRate());
            bidRequest.setDescription(br.getDescription());
            bidRequest.setDisableDays(br.getDisableDays());
            bidRequest.setMinBidAmount(br.getMinBidAmount());
            bidRequest.setReturnType(br.getReturnType());
            bidRequest.setMonthes2Return(br.getMonthes2Return());
            bidRequest.setTitle(br.getTitle());
            // 2,设置相关值;
            bidRequest.setApplyTime(new Date());
            bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
            bidRequest.setCreateUser(UserContext.getCurrent());
            bidRequest.setTotalRewardAmount(CalculatetUtil.calTotalInterest(
                            bidRequest.getReturnType(),
                            bidRequest.getBidRequestAmount(),
                            bidRequest.getCurrentRate(),
                            bidRequest.getMonthes2Return()));
            // 3. 保存
            bidRequestMapper.insert(bidRequest);
            // 4. 给借款人添加一个状态码
            Userinfo userinfo = userinfoService.getCurrent();
            userinfo.addState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
            userinfoService.update(userinfo);
        }

    }


    public void publishAudit(Long id, String remark, int state) {
        // 查出BidRequest
        BidRequest br = bidRequestMapper.selectByPrimaryKey(id);
        // 判断状态
        if (br != null && br.getBidRequestState() == BidConst.BIDREQUEST_STATE_PUBLISH_PENDING) {
            // 创建一个审核历史对象
            BidRequestAuditHistory history = new BidRequestAuditHistory();
            history.setApplier(br.getCreateUser());
            history.setApplyTime(br.getApplyTime());
            history.setAuditType(BidRequestAuditHistory.PUBLISH_AUDIT);
            history.setAuditor(UserContext.getCurrent());
            history.setAuditTime(new Date());
            history.setRemark(remark);
            history.setState(state);
            history.setBidRequestId(br.getId());

            bidRequestAuditHistoryMapper.insert(history);

            if (state == BidRequestAuditHistory.PUBLISH_AUDIT) {
                // 如果审核通过，修改标的状态，设置风控意见
                br.setBidRequestState(BidConst.BIDREQUEST_STATE_BIDDING);
                // 这里有问题，代查看
                br.setDisableDate(DateUtils.addDays(new Date(), br.getDisableDays()));
                br.setPublishTime(new Date());
                br.setNote(remark);
            } else  {
                // 如果审核失败，修改标的状态，用户去掉状态码
                br.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_REFUSE);
                Userinfo applier = userinfoService.get(br.getCreateUser().getId());
                applier.removeState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
                userinfoService.update(applier);
            }
            update(br);

        }






    }
}
