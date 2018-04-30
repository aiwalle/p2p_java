package com.liang.p2p.base.controller;

import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.service.ISystemDictionaryService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.JSONResult;
import com.liang.p2p.base.util.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户个人资料
 * Created by liang on 2018/4/29.
 */
@Controller
public class BasicInfoController {

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequireLogin
    @RequestMapping("basicInfo")
    public String basicInfo(Model model) {
        model.addAttribute("userinfo", userinfoService.getCurrent());
        model.addAttribute("educationBackgrounds", systemDictionaryService.listByParentSn("educationBackgrounds"));
        model.addAttribute("incomeGrades", systemDictionaryService.listByParentSn("incomeGrades"));
        model.addAttribute("marriages", systemDictionaryService.listByParentSn("marriages"));
        model.addAttribute("kidCounts", systemDictionaryService.listByParentSn("kidCounts"));
        model.addAttribute("houseConditions", systemDictionaryService.listByParentSn("houseConditions"));
        return "userInfo";
    }

    @RequireLogin
    @RequestMapping("basicInfo_save")
    @ResponseBody
    public JSONResult basicInfoSave(Userinfo userinfo) {
        userinfoService.updateBasicInfo(userinfo);
        return new JSONResult();
    }


}
