package com.liang.mgrsite.base;

import com.liang.p2p.base.domain.Logininfo;
import com.liang.p2p.base.query.IplogQueryObject;
import com.liang.p2p.base.service.IIplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台查询登录日子Controller
 * Created by liang on 2018/4/25.
 */
@Controller
public class IplogController {
    @Autowired
    private IIplogService iplogService;

    @RequestMapping("ipLog")
    public String ipLog(@ModelAttribute("qo") IplogQueryObject qo, Model model) {
        model.addAttribute("pageResult", iplogService.query(qo));
        return "ipLog/list";
    }


}
