package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.MailVerify;
import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.mapper.MailVerifyMapper;
import com.liang.p2p.base.mapper.UserinfoMapper;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.service.IVerifyCodeService;
import com.liang.p2p.base.util.BidConst;
import com.liang.p2p.base.util.BitStatesUtils;
import com.liang.p2p.base.util.DateUtil;
import com.liang.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by liang on 2018/4/24.
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {

    @Value("${mail.hostUrl}")
    private String hostUrl;

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private IVerifyCodeService verifyCodeService;

    @Autowired
    private MailVerifyMapper mailVerifyMapper;

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

    public void sendVerifyEmail(String email) {
        Userinfo current = this.getCurrent();
        // 当前用户没有绑定邮箱
        if (!current.getIsBindEmail()) {
            String uuid = UUID.randomUUID().toString();
            // 构造一份要发送的邮件
            StringBuilder content = new StringBuilder(100)
                    .append("点击<a href='").append(this.hostUrl)
                    .append("bindEmail.do?key=").append(uuid)
                    .append("'>这里</a>完成邮箱绑定,有效期为")
                    .append(BidConst.VERIFYEMAIL_VAILDATE_DAY).append("天");

            try {
                System.out.println("发送邮件:" + email +"邮件内容 = " + content);
                // 创建一个MailVerify对象
                MailVerify mv = new MailVerify();
                mv.setEmail(email);
                mv.setSendDate(new Date());
                mv.setUserinfoId(current.getId());
                mv.setUuid(uuid);
                this.mailVerifyMapper.insert(mv);
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new RuntimeException("验证邮件发送失败");
            }



            // 执行邮件发送

        }

    }


    public Userinfo getCurrent() {
        return this.get(UserContext.getCurrent().getId());
    }

    public void bindEmail(String uuid) {
        // 通过uuid得到mailverify对象
        MailVerify mailVerify = mailVerifyMapper.selectByUUID(uuid);
        if (mailVerify != null) {
            // 判断用户没有绑定邮箱
            Userinfo current = this.get(mailVerify.getUserinfoId());
            if (!current.getIsBindEmail()) {
                // 判断有效期
                if (mailVerify != null && DateUtil.secondsBetween(new Date(), mailVerify.getSendDate()) <= BidConst.VERIFYEMAIL_VAILDATE_DAY * 24 * 3600) {
                    // 修改用户状态码，给用户设置邮箱
                    current.addState(BitStatesUtils.OP_BIND_EMAIL);
                    current.setEmail(mailVerify.getEmail());
                    this.update(current);
                    return;
                }
            }
        }
        throw new RuntimeException("绑定邮箱失败");
    }


}
