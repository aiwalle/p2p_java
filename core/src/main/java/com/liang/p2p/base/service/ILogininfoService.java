package com.liang.p2p.base.service;

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
}
