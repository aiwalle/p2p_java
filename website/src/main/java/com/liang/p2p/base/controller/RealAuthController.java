package com.liang.p2p.base.controller;

import com.liang.p2p.base.domain.RealAuth;
import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.service.IRealAuthService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.JSONResult;
import com.liang.p2p.base.util.RequireLogin;
import com.liang.p2p.base.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

/**
 * 实名认证控制
 * Created by liang on 2018/4/30.
 */
@Controller
public class RealAuthController {

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IRealAuthService realAuthService;

    @Autowired
    private ServletContext servletContext;


    @RequireLogin
    @RequestMapping("realAuth")
    public String realAuth(Model model) {
        // 1.得到当前userinfo
        Userinfo current = userinfoService.getCurrent();
        // 2.如果用户已经实名认证
        if (current.getIsRealAuth()) {
            //      根据userinfo上的realAuthId得到实名认证对象信息,并放到model中
            model.addAttribute("realAuth", realAuthService.get(current.getRealAuthId()));
            //      auditing = false
            model.addAttribute("auditing", false);
            model.addAttribute("userinfo", userinfoService.getCurrent());
            return "realAuth_result";
        } else {
            // 3.如果用户没有实名认证
            //      1.userinfo上有realAuthId,auditing=true
            if (current.getRealAuthId() != null) {
                model.addAttribute("auditing", true);
                return "realAuth_result";
            }
            //      2.userinfo上没有realAuthId,跳转到realAuth
            return "realAuth";
        }
    }

    /**
     * 千万不要加RequireLogin
     * @param file
     */
    @RequestMapping("realAuthUpload")
    @ResponseBody
    public String realAuthUpload(MultipartFile file) {
        String basePath = servletContext.getRealPath("/upload");
        String fileName = UploadUtil.upload(file, basePath);
        return  "/upload/" + fileName;
    }

    /**
     * 申请实名认证
     */
    @RequireLogin
    @RequestMapping("realAuth_save")
    @ResponseBody
    public JSONResult realAuthSave(RealAuth realAuth) {
        realAuthService.apply(realAuth);
        return new JSONResult();
    }
}
