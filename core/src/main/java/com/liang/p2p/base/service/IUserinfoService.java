package com.liang.p2p.base.service;

import com.liang.p2p.base.domain.Userinfo;

/**
 * Created by liang on 2018/4/24.
 */
public interface IUserinfoService {

    /**
     * 记住，写完mapper以后立刻写service，因为这个update是支持乐观锁的
     * @param userinfo
     */
    void update(Userinfo userinfo);


    /**
     * 添加userinfo
     * @param userinfo
     */
    void add(Userinfo userinfo);

    Userinfo get(Long id);

    /**
     * 绑定手机号
     * @param phoneNumber
     * @param verifyCode
     */
    void bindPhone(String phoneNumber, String verifyCode);
}
