package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.mapper.UserinfoMapper;
import com.liang.p2p.base.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liang on 2018/4/24.
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    public void update(Userinfo userinfo) {
        int result = userinfoMapper.updateByPrimaryKey(userinfo);
        if (result == 0) {
            throw  new RuntimeException("乐观锁失败，Userinfo:" + userinfo.getId());
        }
    }

    public void add(Userinfo userinfo) {
        userinfoMapper.insert(userinfo);
    }

    public Userinfo get(Long id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }
}
