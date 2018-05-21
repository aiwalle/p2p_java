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

//    /**
//     * 列出所有的BidRequest,废弃这种查询的方式而通过queryObject来灵活查询
//     * @return
//     */
//    List<BidRequest> listIndex();
}