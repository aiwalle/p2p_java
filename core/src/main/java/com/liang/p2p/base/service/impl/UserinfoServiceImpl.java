package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.mapper.UserinfoMapper;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.service.IVerifyCodeService;
import com.liang.p2p.base.util.BitStatesUtils;
import com.liang.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liang on 2018/4/24.
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private IVerifyCodeService verifyCodeService;

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

    public void bindPhone(String phoneNumber, String verifyCode) {
        Userinfo current =  this.get(UserContext.getCurrent().getId());
        if (!current.getIsBindPhone()) {
            // 验证验证码合法
            boolean ret = verifyCodeService.verifyCode(phoneNumber, verifyCode);
            if (ret) {
                current.addState(BitStatesUtils.OP_BIND_PHONE);
                current.setPhoneNumber(phoneNumber);
                this.update(current);
            } else {
                throw new RuntimeException("绑定手机失败");
            }

        }

    }
}
