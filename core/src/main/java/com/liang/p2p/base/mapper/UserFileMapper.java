package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.UserFile;
import java.util.List;

public interface UserFileMapper {

    int insert(UserFile record);

    UserFile selectByPrimaryKey(Long id);

    List<UserFile> selectAll();

    int updateByPrimaryKey(UserFile record);

    /**
     * 列出一个没有选择风控材料类型的风控资料对象
     * @param logininfoId
     * @return
     */
    List<UserFile> listUnTypeFiles(Long logininfoId);
}