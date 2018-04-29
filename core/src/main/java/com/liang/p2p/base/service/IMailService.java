package com.liang.p2p.base.service;

/**
 * 专门用于发送邮件的服务
 * Created by liang on 2018/4/27.
 */
public interface IMailService {

    /**
     * 发送邮件
     * @param target 目标邮件地址
     * @param title
     * @param content
     */
    void sendMail(String target, String title, String content);
}
