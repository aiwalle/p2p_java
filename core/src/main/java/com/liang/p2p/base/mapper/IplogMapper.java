package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.Iplog;
import com.liang.p2p.base.query.IplogQueryObject;

import java.util.List;

public interface IplogMapper {

    int insert(Iplog record);

    /**
     * 高级查询总数
     * @param queryObject
     * @return
     */
    int queryForCount(IplogQueryObject queryObject);

    /**
     * 查询当前页数据
     * @param queryObject
     * @return
     */
    List<Iplog> query(IplogQueryObject queryObject);
}