package com.liang.p2p.base.controller;

import com.liang.p2p.base.service.IVerifyCodeService;
import com.liang.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 验证码相关
 * Created by liang on 2018/4/25.
 */
@Controller
public class VerifyCodeController {

    @Autowired
    private IVerifyCodeService verifyCodeService;


    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public JSONResult sendVerifyCode(String phoneNumber) {
        System.out.println("phoneNumber = " + phoneNumber);
        JSONResult jsonResult = new JSONResult();
        try {
            verifyCodeService.sendVerifyCode(phoneNumber);
        } catch (RuntimeException e) {
            jsonResult.setMsg(e.getMessage());
            jsonResult.setSuccess(false);
        }

        return jsonResult;
    }

}
