package com.liang.p2p.base.controller;

import com.liang.p2p.base.util.BidConst;
import com.liang.p2p.business.query.BidRequestQueryObject;
import com.liang.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 网站首页
 * Created by liang on 2018/5/21.
 */
@Controller
public class IndexController {

    @Autowired
    private IBidRequestService bidRequestService;


    @RequestMapping("index")
    public String index(Model model) {
        //bidRequests
        model.addAttribute("bidRequests", bidRequestService.listIndex(5));
        return "main";
    }

    /**
     * 投资列表的框框
     * @return
     */
    @RequestMapping("invest")
    public String investIndex() {
        return "invest";
    }

    /**
     * 投资列表明细
     */
    @RequestMapping("invest_list")
    public String investList(BidRequestQueryObject qo, Model model) {
        if (qo.getBidRequestState() == -1) {
            qo.setBidRequestStates(new int[] { BidConst.BIDREQUEST_STATE_BIDDING,
                    BidConst.BIDREQUEST_STATE_PAYING_BACK,
                    BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK });
        }
        model.addAttribute("pageResult", bidRequestService.query(qo));
        return "invest_list";
    }

}
