package com.liang.p2p.base.service;

import com.liang.p2p.base.domain.Account;

/**
 * 账户相关服务
 * Created by liang on 2018/4/24.
 */
public interface IAccountService {

    /**
     * 记住，写完mapper以后立刻写service，因为这个update是支持乐观锁的
     * @param account
     */
    void update(Account account);

    void add(Account account);

    Account get(Long id);

    /**
     * 得到当前登录用户对应的账户信息
     * @return
     */
    Account getCurrent();
}
