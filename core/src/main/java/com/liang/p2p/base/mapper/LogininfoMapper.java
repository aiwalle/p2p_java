package com.liang.p2p.base.mapper;

import com.liang.p2p.base.domain.Logininfo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 用户登录
     * @param username
     * @param password
     * @param userType
     * @return
     */
    Logininfo login(@Param("username") String username, @Param("password") String password, @Param("userType") int userType);

    /**
     * 按照类型查询用户数量
     * @param userType
     * @return
     */
    int countByUserType(int userType);
}