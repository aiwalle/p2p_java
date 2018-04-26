package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.service.IVerifyCodeService;
import com.liang.p2p.base.util.BidConst;
import com.liang.p2p.base.util.DateUtil;
import com.liang.p2p.base.util.UserContext;
import com.liang.p2p.base.vo.VerifyCodeVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by liang on 2018/4/25.
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {


    public void sendVerifyCode(String phoneNumber) {
        VerifyCodeVO vc = UserContext.getCurrentVerifyCode();
        if (vc == null || DateUtil.secondsBetween(new Date(), vc.getLastSendTime()) > 90) {
            // 正常发送验证码短信
            String verifyCode = UUID.randomUUID().toString().substring(0, 4);

            System.out.println("给手机"+ phoneNumber + "发送验证码：" + verifyCode);

            vc = new VerifyCodeVO();
            vc.setLastSendTime(new Date());
            vc.setVerifyCode(verifyCode);
            vc.setPhoneNumber(phoneNumber);
            UserContext.putVerifyCode(vc);
        } else {
            throw new RuntimeException("发送过于频繁");
        }

    }

    public boolean verifyCode(String phoneNumber, String verifyCode) {
        VerifyCodeVO vc = UserContext.getCurrentVerifyCode();
        if (vc != null   // 发送了验证码
                && vc.getPhoneNumber().equals(phoneNumber)   // 手机号相同
                && vc.getVerifyCode().equalsIgnoreCase(verifyCode) // 验证码相同
                && DateUtil.secondsBetween(new Date(), vc.getLastSendTime()) <= BidConst.VERIFYCODE_VALIDATE_SECOND) // 间隔时间为5分钟内
        {
            return true;
        }
        return false;
    }
}
