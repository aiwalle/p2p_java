package com.liang.mgrsite.base;

import com.liang.p2p.base.query.RealAuthQueryObject;
import com.liang.p2p.base.service.IRealAuthService;
import com.liang.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 实名认证审核相关
 * Created by liang on 2018/5/2.
 */
@Controller
public class RealAuthController {

    @Autowired
    private IRealAuthService realAuthService;

    @RequestMapping("realAuth")
    public String realAuth(@ModelAttribute("qo")RealAuthQueryObject qo, Model model) {
        model.addAttribute("pageResult", realAuthService.query(qo));
        return "realAuth/list";
    }


    /**
     * 实名认证审核
     * @param id
     * @param remark
     * @param state
     * @return
     */
    @RequestMapping("realAuth_audit")
    @ResponseBody
    public JSONResult realAuthAudit(Long id, String remark, int state) {
        realAuthService.audit(id, remark, state);
        return new JSONResult();
    }

}
