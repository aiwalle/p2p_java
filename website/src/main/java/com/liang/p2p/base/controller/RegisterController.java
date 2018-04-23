package com.liang.p2p.base.controller;

import com.liang.p2p.base.service.ILogininfoService;
import com.liang.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户注册相关
 * Created by liang on 2018/4/20.
 */
@Controller
public class RegisterController {
    @Autowired
    private ILogininfoService logininfoService;

    @RequestMapping("/register")
    @ResponseBody
    public JSONResult register(String username, String password) {
        JSONResult json = new JSONResult();
        try {
            logininfoService.register(username, password);
        } catch (RuntimeException re) {
            json.setSuccess(false);
            json.setMsg(re.getMessage());
        }
        return json;
    }


    @RequestMapping("/checkUsername")
    @ResponseBody
    public boolean checkUsername(String username) {
        return !logininfoService.checkUsername(username);
    }

    @RequestMapping("/login")
    @ResponseBody
    public JSONResult login(String username, String password) {
        JSONResult json = new JSONResult();
        try {
            logininfoService.login(username, password);
        } catch (RuntimeException re) {
            json.setSuccess(false);
            json.setMsg(re.getMessage());
        }
        return json;
    }

}
