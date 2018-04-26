package com.liang.p2p.base.service;

import com.liang.p2p.base.query.IplogQueryObject;
import com.liang.p2p.base.query.PageResult;

/**
 * Created by liang on 2018/4/25.
 */
public interface IIplogService {

    /**
     * 分页查询
     * @param queryObject
     * @return
     */
    PageResult query(IplogQueryObject queryObject);
}
