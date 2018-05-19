package com.liang.mgrsite.base;

import com.liang.p2p.base.util.BidConst;
import com.liang.p2p.base.util.JSONResult;
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



    @RequestMapping("bidrequest_publishaudit_list")
    public String bidRequestPublishAuditList(@ModelAttribute("qo")BidRequestQueryObject qo, Model model) {
        qo.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
        model.addAttribute("pageResult", bidRequestService.query(qo));
        return "bidrequest/publish_audit";
    }

    @RequestMapping("bidrequest_publishaudit")
    @ResponseBody
    public JSONResult bidRequestPublishAudit(Long id, String remark, int state) {
        bidRequestService.publishAudit(id, remark, state);
        return new JSONResult();
    }

}
