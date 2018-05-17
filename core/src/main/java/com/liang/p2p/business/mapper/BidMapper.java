package com.liang.p2p.business.mapper;

import com.liang.p2p.business.domain.Bid;
import java.util.List;

public interface BidMapper {

    int insert(Bid record);

    Bid selectByPrimaryKey(Long id);

//    List<Bid> selectAll();

}