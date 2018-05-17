package com.liang.p2p.base.service;

import com.liang.p2p.base.domain.UserFile;
import com.liang.p2p.base.query.IplogQueryObject;
import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.query.UserFileQueryObject;

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
     * 列出一个用户风控资料对象
     * @param logininfoId
     * @param hasType  如果为true,表示有类型，如果为false，表示没有类型
     * @return
     */
    List<UserFile> listFilesByHasType(Long logininfoId, boolean hasType);

    /**
     * 批量的处理用户风控资料类型的选择
     * @param ids
     * @param fileTypes
     */
    void batchUpdateFileType(Long[] ids, Long[] fileTypes);

    /**
     * 分页查询
     * @param queryObject
     * @return
     */
    PageResult query(UserFileQueryObject queryObject);

    /**
     * 审核
     * @param id
     * @param score
     * @param remark
     * @param state
     */
    void audit(Long id, int score, String remark, int state);
}
