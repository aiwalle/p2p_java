package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.Iplog;
import java.util.List;

public interface IplogMapper {

    int insert(Iplog record);

    List<Iplog> selectAll();

}