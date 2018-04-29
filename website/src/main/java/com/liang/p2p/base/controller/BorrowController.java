package com.liang.p2p.base.controller;

import com.liang.p2p.base.domain.Logininfo;
import com.liang.p2p.base.service.IAccountService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.BidConst;
import com.liang.p2p.base.util.UserContext;
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
}
