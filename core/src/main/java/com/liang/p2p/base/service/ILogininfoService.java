package com.liang.p2p.base.service;

import com.liang.p2p.base.domain.Logininfo;

/**
 * 登录相关服务
 * Created by liang on 2018/4/13.
 */
public interface ILogininfoService {

    /**
     * 用户注册
     *
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 检查用户名是否存在，存在true，不存在false
     * @param username
     * @return
     */
    boolean checkUsername(String username);


    /**
     * 用户登录
     * @param username
     * @param password
     * @param remoteAddr
     */
    Logininfo login(String username, String password, String remoteAddr);
}
