package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.Iplog;
import com.liang.p2p.base.domain.UserFile;
import com.liang.p2p.base.query.UserFileQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFileMapper {

    int insert(UserFile record);

    UserFile selectByPrimaryKey(Long id);

    List<UserFile> selectAll();

    int updateByPrimaryKey(UserFile record);

    /**
     * 列出一个用户风控资料对象
     * @param logininfoId
     * @param hasType  如果为true,表示有类型，如果为false，表示没有类型
     * @return
     */
    List<UserFile> listFilesByHasType(@Param("logininfoId") Long logininfoId, @Param("hasType") boolean hasType);


    /**
     * 高级查询总数
     * @param queryObject
     * @return
     */
    int queryForCount(UserFileQueryObject queryObject);

    /**
     * 查询当前页数据
     * @param queryObject
     * @return
     */
    List<UserFile> query(UserFileQueryObject queryObject);
}