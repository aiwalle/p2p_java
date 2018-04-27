package com.liang.p2p.base.controller;

import com.liang.p2p.base.domain.Logininfo;
import com.liang.p2p.base.service.IAccountService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.JSONResult;
import com.liang.p2p.base.util.RequireLogin;
import com.liang.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 个人中心
 * Created by liang on 2018/4/24.
 */
@Controller
public class PersonalController {

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IAccountService accountService;

    @RequireLogin
    @RequestMapping("personal")
    public String personalCenter(Model model){
        Logininfo logininfo = UserContext.getCurrent();
        model.addAttribute("userinfo", userinfoService.get(logininfo.getId()));
        model.addAttribute("account",accountService.get(logininfo.getId()));
        return "personal";
    }

    /**
     * 绑定手机
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    @RequireLogin
    @RequestMapping("bindPhone")
    @ResponseBody
    public JSONResult bindPhone(String phoneNumber, String verifyCode) {
        JSONResult jsonResult = new JSONResult();
        try {
            userinfoService.bindPhone(phoneNumber, verifyCode);
        } catch (RuntimeException e) {
            jsonResult.setMsg(e.getMessage());
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }


    /**
     * 发送邮件
     * @param email
     * @return
     */
    @RequireLogin
    @RequestMapping("sendEmail")
    @ResponseBody
    public JSONResult sendEmail(String email) {
        JSONResult jsonResult = new JSONResult();
        try {
            userinfoService.sendVerifyEmail(email);
        } catch (RuntimeException e) {
            jsonResult.setMsg(e.getMessage());
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }



    /**
     * 绑定邮箱
     * @param key
     * @return
     */
    @RequestMapping("bindEmail")
    public String bindEmail(String key, Model model) {
        try {
            this.userinfoService.bindEmail(key);
            model.addAttribute("success", true);
        } catch (RuntimeException e) {
            model.addAttribute("success", false);
            model.addAttribute("msg", e.getMessage());
        }
        return "checkmail_result";
    }

}
