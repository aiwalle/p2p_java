package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.SystemDictionaryItem;
import com.liang.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SystemDictionaryItem record);


    /**
     * 分页相关的查询
     * @param qo
     * @return
     */
    int queryForCount(SystemDictionaryQueryObject qo);
    List<SystemDictionaryItem> query(SystemDictionaryQueryObject qo);

    List<SystemDictionaryItem> listByParentSn(String sn);
}