package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.Account;
import com.liang.p2p.base.mapper.AccountMapper;
import com.liang.p2p.base.service.IAccountService;
import com.liang.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liang on 2018/4/24.
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    public void update(Account account) {
        int result = accountMapper.updateByPrimaryKey(account);
        if (result == 0) {
            throw new RuntimeException("乐观锁失败，Account："+ account.getId());
        }


    }

    public void add(Account account) {
        accountMapper.insert(account);
    }

    public Account get(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    public Account getCurrent() {
        return this.get(UserContext.getCurrent().getId());
    }


}
