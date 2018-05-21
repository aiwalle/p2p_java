package com.liang.p2p.business.service;

import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.business.domain.BidRequest;
import com.liang.p2p.business.domain.BidRequestAuditHistory;
import com.liang.p2p.business.query.BidRequestQueryObject;

import java.util.List;

/**
 * 借款相关
 * Created by liang on 2018/5/17.
 */
public interface IBidRequestService {


    void update(BidRequest bidRequest);

    BidRequest get(Long id);

    /**
     * 判断用户是否具有申请借款的权利
     * @return
     */
    boolean canApplyBidRequest(Long logininfoId);

    /**
     * 申请借款
     * @param bidRequest
     */
    void apply(BidRequest bidRequest);

    /**
     * 根据一个标查询该标的审核历史
     * @param id
     * @return
     */
    List<BidRequestAuditHistory> listAuditHistoryByBidRequest(Long id);


    PageResult query(BidRequestQueryObject qo);

    /**
     * 发标前审核
     * @param id
     * @param remark
     * @param state
     */
    void publishAudit(Long id, String remark, int state);

    /**
     * 查询首页数据
     * @return
     */
    List<BidRequest> listIndex(int size);
}
