package com.liang.p2p.business.mapper;

import com.liang.p2p.business.domain.BidRequest;
import com.liang.p2p.business.query.BidRequestQueryObject;

import java.util.List;

public interface BidRequestMapper {

    int insert(BidRequest record);

    BidRequest selectByPrimaryKey(Long id);

    int updateByPrimaryKey(BidRequest record);


    /**
     * 查询相关
     */
    int queryForCount(BidRequestQueryObject qo);
    List<BidRequest> query(BidRequestQueryObject qo);
}