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
        List<UserFile> userFiles = userFileService.listFilesByHasType(UserContext.getCurrent().getId(), false);

        // 如果有未选择的用户文件类型，设置数据字典，并前往userFiles_commit
        if (userFiles.size() > 0) {
            model.addAttribute("fileTypes", systemDictionaryService.listByParentSn("userFileType"));
            model.addAttribute("userFiles", userFiles);
            return "userFiles_commit";
        } else {
            // 选择所有该用户的风控文件
            // 往userfile
            model.addAttribute("sessionid", request.getSession().getId());
            userFiles = userFileService.listFilesByHasType(UserContext.getCurrent().getId(), true);
            model.addAttribute("userFiles", userFiles);
            return "userFiles";
        }
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
