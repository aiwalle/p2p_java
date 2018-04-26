package com.liang.p2p.base.service;

/**
 * 手机验证码相关服务
 * Created by liang on 2018/4/25.
 */
public interface IVerifyCodeService {
    /**
     * 给指定的手机发送验证码
     * @param phoneNumber
     */
    void sendVerifyCode(String phoneNumber);

    /**
     * 用于验证手机验证码的
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    boolean verifyCode(String phoneNumber, String verifyCode);
}
