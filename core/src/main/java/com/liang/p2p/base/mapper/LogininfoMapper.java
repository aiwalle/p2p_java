package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.Logininfo;
import java.util.List;

public interface LogininfoMapper {

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);


    /**
     * 根据用户名查询用户数量
     * @param username
     * @return
     */
    int getCountByUsername(String username);
}