package com.liang.mgrsite.base;

import com.liang.p2p.base.domain.UserFile;
import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.query.UserFileQueryObject;
import com.liang.p2p.base.service.IRealAuthService;
import com.liang.p2p.base.service.IUserFileService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.BidConst;
import com.liang.p2p.base.util.JSONResult;
import com.liang.p2p.business.domain.BidRequest;
import com.liang.p2p.business.query.BidRequestQueryObject;
import com.liang.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 借款相关Controller
 * Created by liang on 2018/5/18.
 */
@Controller
public class BidRequestController {

    @Autowired
    private IBidRequestService bidRequestService;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IRealAuthService realAuthService;

    @Autowired
    private IUserFileService userFileService;


    @RequestMapping("bidrequest_publishaudit_list")
    public String bidRequestPublishAuditList(@ModelAttribute("qo")BidRequestQueryObject qo, Model model) {
        qo.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
        model.addAttribute("pageResult", bidRequestService.query(qo));
        return "bidrequest/publish_audit";
    }


    /**
     * 发表前审核
     * @param id
     * @param remark
     * @param state
     * @return
     */
    @RequestMapping("bidrequest_publishaudit")
    @ResponseBody
    public JSONResult bidRequestPublishAudit(Long id, String remark, int state) {
        bidRequestService.publishAudit(id, remark, state);
        return new JSONResult();
    }

    /**
     * 后台查看借款详情
     */
    @RequestMapping("borrow_info")
    public String borrowInfo(Long id, Model model) {
        //bidRequest
        BidRequest bidRequest = bidRequestService.get(id);
        Userinfo userinfo = userinfoService.get(bidRequest.getCreateUser().getId());
        model.addAttribute("bidRequest", bidRequest);
        //userInfo
        model.addAttribute("userInfo", userinfo);
        //audits:这个标的审核历史
        model.addAttribute("audits", bidRequestService.listAuditHistoryByBidRequest(id));
        //realAuth:借款人实名认证信息
        model.addAttribute("realAuth", realAuthService.get(userinfo.getRealAuthId()));
        //userFiles:借款人的风控资料信息
        UserFileQueryObject qo = new UserFileQueryObject();
        qo.setApplierId(userinfo.getId());
        qo.setState(UserFile.STATE_AUDIT);
        qo.setPageSize(-1);
        model.addAttribute("userFiles", userFileService.queryForList(qo));
        return "bidrequest/borrow_info";
    }

}
