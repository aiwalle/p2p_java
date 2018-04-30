package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.RealAuth;

public interface RealAuthMapper {

    int insert(RealAuth record);

    RealAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RealAuth record);
}