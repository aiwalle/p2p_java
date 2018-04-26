package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.Iplog;
import com.liang.p2p.base.mapper.IplogMapper;
import com.liang.p2p.base.query.IplogQueryObject;
import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.service.IIplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liang on 2018/4/25.
 */
@Service
public class IplogServiceImpl implements IIplogService {

    @Autowired
    private IplogMapper iplogMapper;

    public PageResult query(IplogQueryObject queryObject) {
        int count = iplogMapper.queryForCount(queryObject);
        if (count >0) {
            List<Iplog> list = iplogMapper.query(queryObject);
            return new PageResult(list,count,queryObject.getCurrentPage(),queryObject.getPageSize());
        }
        return PageResult.empty(queryObject.getPageSize());
    }



}
