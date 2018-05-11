package com.liang.p2p.base.controller;

import com.liang.p2p.base.domain.UserFile;
import com.liang.p2p.base.service.ISystemDictionaryService;
import com.liang.p2p.base.service.IUserFileService;
import com.liang.p2p.base.util.JSONResult;
import com.liang.p2p.base.util.UploadUtil;
import com.liang.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 风控资料相关
 * Created by liang on 2018/5/6.
 */
@Controller
public class UserFileController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private IUserFileService userFileService;


    @Autowired
    private ISystemDictionaryService systemDictionaryService;


    @RequestMapping("userFile")
    public String userFile(Model model, HttpServletRequest request) {
        model.addAttribute("sessionid", request.getSession().getId());
        List<UserFile> userFiles = userFileService.listUnTypeFiles(UserContext.getCurrent().getId());
        model.addAttribute("userFiles", userFiles);
        model.addAttribute("fileTypes", systemDictionaryService.listByParentSn("userFileType"));
//        return "userFiles";
        return "userFiles_commit";
    }


    /**
     * 处理上传用户风控文件
     */
    @RequestMapping("userFileUpload")
    @ResponseBody
    public void userFileUpload(MultipartFile file) {
        String basePath = servletContext.getRealPath("/upload");
        String fileName = UploadUtil.upload(file, basePath);
        fileName = "upload/" + fileName;
        userFileService.apply(fileName);
    }


    /**
     * 处理用户风控文件选择
     */
    @RequestMapping("userFile_selectType")
    @ResponseBody
    public JSONResult userFileSelectType(Long[] fileType, Long[] id) {
        if (fileType != null && id != null && fileType.length == id.length) {
            userFileService.batchUpdateFileType(id, fileType);
        }
        return new JSONResult();
    }
}
