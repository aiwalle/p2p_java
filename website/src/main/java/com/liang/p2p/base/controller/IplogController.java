package com.liang.p2p.base.controller;

import com.liang.p2p.base.query.IplogQueryObject;
import com.liang.p2p.base.service.IIplogService;
import com.liang.p2p.base.util.RequireLogin;
import com.liang.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录日志
 * Created by liang on 2018/4/25.
 */
@Controller
public class IplogController {

    @Autowired
    private IIplogService iplogService;


    /**
     * 个人用户登录日志列表
     * @param qo
     * @param model
     * @return
     */
    @RequireLogin
    @RequestMapping("ipLog")
    public String iplogList(@ModelAttribute("qo") IplogQueryObject qo, Model model) {
        // 这里我们只查询自己的登录记录
        qo.setUsername(UserContext.getCurrent().getUsername());
        model.addAttribute("pageResult", iplogService.query(qo));
        return "iplog_list";
    }


}
