package com.liang.mgrsite.base;

import com.liang.p2p.base.query.VedioAuthQueryObject;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.service.IVedioAuthService;
import com.liang.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *
 * 视频认证Controller
 * Created by liang on 2018/5/3.
 */
@Controller
public class VedioAuthController {

    @Autowired
    private IVedioAuthService vedioAuthService;

    @Autowired
    private IUserinfoService userinfoService;


    @RequestMapping("vedioAuth")
    public String vedioAuth(@ModelAttribute("qo")VedioAuthQueryObject qo, Model model) {
        model.addAttribute("pageResult", vedioAuthService.query(qo));
        return "vedioAuth/list";
    }


    /**
     * 完成视频审核
     */
    @RequestMapping("vedioAuth_audit")
    @ResponseBody
    public JSONResult vedioAuthAudit(Long loginInfoValue, String remark, int state) {
        vedioAuthService.audit(loginInfoValue,remark,state);
        return new JSONResult();
    }


    /**
     * 用于用户的autoComplete
     */
    @RequestMapping("vedioAuth_autocomplate")
    @ResponseBody
    public List<Map<String, Object>> autoComplate(String keyword) {
        return userinfoService.autoComplate(keyword);
    }
}
