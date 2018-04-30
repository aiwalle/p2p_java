package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.SystemDictionary;
import com.liang.p2p.base.domain.SystemDictionaryItem;
import com.liang.p2p.base.mapper.SystemDictionaryItemMapper;
import com.liang.p2p.base.mapper.SystemDictionaryMapper;
import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.query.SystemDictionaryQueryObject;
import com.liang.p2p.base.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liang on 2018/4/27.
 */
@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;

    public PageResult queryDics(SystemDictionaryQueryObject qo) {
        int count = systemDictionaryMapper.queryForCount(qo);
        if (count > 0) {
            List<SystemDictionary> list = systemDictionaryMapper.query(qo);
            return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
        }
        return PageResult.empty(qo.getPageSize());
    }

    public void saveOrUpdateDic(SystemDictionary systemDictionary) {
        if (systemDictionary.getId() != null) {
            systemDictionaryMapper.updateByPrimaryKey(systemDictionary);
        } else {
            systemDictionaryMapper.insert(systemDictionary);
        }
    }

    public PageResult queryItems(SystemDictionaryQueryObject qo) {
        int count = systemDictionaryItemMapper.queryForCount(qo);
        if (count > 0) {
            List<SystemDictionaryItem> list = systemDictionaryItemMapper.query(qo);
            return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
        }
        return PageResult.empty(qo.getPageSize());
    }

    public List<SystemDictionary> listAllDics() {
        return systemDictionaryMapper.selectAll();
    }

    public void saveOrUpdateItem(SystemDictionaryItem item) {
        if (item.getId() != null) {
            systemDictionaryItemMapper.updateByPrimaryKey(item);
        } else {
            systemDictionaryItemMapper.insert(item);
        }
    }

    public List<SystemDictionaryItem> listByParentSn(String sn) {
        return systemDictionaryItemMapper.listByParentSn(sn);
    }
}
