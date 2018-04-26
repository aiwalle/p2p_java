package com.liang.mgrsite.base;

import com.liang.p2p.base.domain.Logininfo;
import com.liang.p2p.base.service.ILogininfoService;
import com.liang.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台登录
 * Created by liang on 2018/4/25.
 */
@Controller
public class LoginController {
    @Autowired
    private ILogininfoService logininfoService;


    /**
     * 后台登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public JSONResult login(String username, String password, HttpServletRequest request) {
        JSONResult json = new JSONResult();
        Logininfo logininfo = logininfoService.login(username, password, request.getRemoteAddr(), Logininfo.USER_MANAGER);
        if (logininfo == null) {
            json.setSuccess(false);
            json.setMsg("用户名密码错误");
        }
        return json;
    }


    /**
     * 后台首页
     * @return
     */
    @RequestMapping("index")
    public String index() {

        return "main";
    }

}
