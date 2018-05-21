package com.liang.p2p.base.controller;

import com.liang.p2p.base.domain.Logininfo;
import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.query.UserFileQueryObject;
import com.liang.p2p.base.service.IAccountService;
import com.liang.p2p.base.service.IRealAuthService;
import com.liang.p2p.base.service.IUserFileService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.BidConst;
import com.liang.p2p.base.util.UserContext;
import com.liang.p2p.business.domain.BidRequest;
import com.liang.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 借款申请相关的控制器
 * Created by liang on 2018/4/27.
 */
@Controller
public class BorrowController {


    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IBidRequestService bidRequestService;

    @Autowired
    private IRealAuthService realAuthService;

    @Autowired
    private IUserFileService userFileService;

    /**
     * 导向到借款首页
     * @return
     */
    @RequestMapping("borrow")
    public String borrowIndex(Model model) {
        Logininfo logininfo = UserContext.getCurrent();
        if (logininfo == null) {
            return "redirect:borrow.html";
        } else  {
            model.addAttribute("account", accountService.getCurrent());
            model.addAttribute("userinfo", userinfoService.getCurrent());
            model.addAttribute("creditBorrowScore", BidConst.BASE_BORROW_SCORE);
            return "borrow";
        }
    }


    /**
     * 导向到借款申请界面
     */
    @RequestMapping("borrowInfo")
    public String borrowInfo(Model model) {
        Long id = UserContext.getCurrent().getId();
        if (bidRequestService.canApplyBidRequest(id)) {
            // 能够申请借款
            model.addAttribute("minBidRequestAmount", BidConst.SMALLEST_BIDREQUEST_AMOUNT);//最小借款金额
            model.addAttribute("minBidAmount", BidConst.SMALLEST_BID_AMOUNT);//系统规定的最小投标金额
            model.addAttribute("account", accountService.getCurrent());
            return "borrow_apply";
        }
        return "borrow_apply_result";
    }

    /**
     * 借款申请
     * @param bidRequest
     * @return
     */
    @RequestMapping("borrow_apply")
    public String borrowApply(BidRequest bidRequest) {
//        boolean isSuccess =
                bidRequestService.apply(bidRequest);
        return "redirect:/borrowInfo.do";
    }

    /**
     * 前端的借款明细
     */
    @RequestMapping("borrow_info")
    public String borrowInfoDetail(Long id, Model model) {
        // bidRequest;
        BidRequest bidRequest = bidRequestService.get(id);
        if (bidRequest != null) {
            // userInfo
            Userinfo applier = userinfoService.get(bidRequest
                    .getCreateUser().getId());
            // realAuth:借款人实名认证信息
            model.addAttribute("realAuth",
                    realAuthService.get(applier.getRealAuthId()));
            // userFiles:借款人风控材料
            UserFileQueryObject qo = new UserFileQueryObject();
            qo.setApplierId(applier.getId());
            qo.setPageSize(-1);
            qo.setCurrentPage(1);
            model.addAttribute("userFiles",
                    userFileService.queryForList(qo));

            model.addAttribute("bidRequest", bidRequest);
            model.addAttribute("userInfo", applier);

            if (UserContext.getCurrent() != null) {
                // self:当前用户是否是借款人自己
                if (UserContext.getCurrent().getId().equals(applier.getId())) {
                    model.addAttribute("self", true);
                } else {
                    // account
                    model.addAttribute("self", false);
                    model.addAttribute("account",
                            this.accountService.getCurrent());
                }
            } else {
                model.addAttribute("self", false);
            }
        }
        return "borrow_info";
    }

}
