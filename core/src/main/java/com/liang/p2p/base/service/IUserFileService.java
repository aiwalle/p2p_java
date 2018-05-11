package com.liang.p2p.base.service;

import com.liang.p2p.base.domain.UserFile;

import java.util.List;

/**
 * 风控资料服务
 * Created by liang on 2018/5/6.
 */
public interface IUserFileService {

    /**
     * 上传一个风控资料对象
     * @param fileName
     */
    void apply(String fileName);


    /**
     * 列出一个没有选择风控材料类型的风控资料对象
     * @param logininfoId
     * @return
     */
    List<UserFile> listUnTypeFiles(Long logininfoId);

    /**
     * 批量的处理用户风控资料类型的选择
     * @param id
     * @param fileType
     */
    void batchUpdateFileType(Long[] ids, Long[] fileTypes);
}
