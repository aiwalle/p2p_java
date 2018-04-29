package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.SystemDictionary;
import com.liang.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryMapper {

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    /**
     * 分页的方法
     * @param qo
     * @return
     */
    int queryForCount(SystemDictionaryQueryObject qo);
    List<SystemDictionary> query(SystemDictionaryQueryObject qo);


    int updateByPrimaryKey(SystemDictionary record);
}