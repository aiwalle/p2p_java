package com.liang.p2p.base.service;

import com.liang.p2p.base.domain.SystemDictionary;
import com.liang.p2p.base.domain.SystemDictionaryItem;
import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

/**
 * 数据字典相关服务
 * Created by liang on 2018/4/27.
 */
public interface ISystemDictionaryService {

    /**
     * 数据字典分类的的分页查询
     * @return
     */
    PageResult queryDics(SystemDictionaryQueryObject qo);

    /**
     * 修改或者保存数据字典分类
     * @param systemDictionary
     */
    void saveOrUpdateDic(SystemDictionary systemDictionary);

    /**
     * 数据字典明细的分页查询
     * @param qo
     * @return
     */
    PageResult queryItems(SystemDictionaryQueryObject qo);

    /**
     * 查询所有的数据字典明细
     * @return
     */
    List<SystemDictionary> listAllDics();

    /**
     * 修改或者保存数据字典明细
     * @param item
     */
    void saveOrUpdateItem(SystemDictionaryItem item);

    /**
     * 根据数据字典分类sn查询明细
     * @param sn
     * @return
     */
    List<SystemDictionaryItem> listByParentSn(String sn);
}
