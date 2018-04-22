package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.Logininfo;
import com.liang.p2p.base.mapper.LogininfoMapper;
import com.liang.p2p.base.service.ILogininfoService;
import com.liang.p2p.base.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liang on 2018/4/20.
 */
@Service
public class LogininfoServiceImpl implements ILogininfoService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    public void register(String username, String password) {
        //判断当前的username是否存在
        int count = logininfoMapper.getCountByUsername(username);

        // 如果不存在，保存
        if (count <= 0) {
            Logininfo logininfo = new Logininfo();
            logininfo.setUsername(username);
            logininfo.setPassword(MD5.encode(password));
            logininfo.setState(Logininfo.STATE_NORMAL);
        } else  {
            // 如果存在，抛出错误
            throw new RuntimeException("用户名已经存在!");
        }


    }
}
