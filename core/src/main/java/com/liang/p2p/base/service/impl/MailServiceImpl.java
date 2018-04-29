package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.service.IMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by liang on 2018/4/27.
 */
@Service
public class MailServiceImpl implements IMailService {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    public void sendMail(String target, String title, String content) {
        try {
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
            senderImpl.setHost(host);
            // 这里必须使用587端口
            senderImpl.setPort(587);
            senderImpl.setDefaultEncoding("UTF-8");

            // 建立邮件消息,发送简单邮件和html邮件的区别
            MimeMessage mailMessage = senderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

            // 设置收件人，寄件人
            messageHelper.setTo(target);
            // from必须要和username一致，神坑
            messageHelper.setFrom(username);
            messageHelper.setSubject(title);
            messageHelper.setText(content, true);

            senderImpl.setUsername(username); // 根据自己的情况,设置username
            senderImpl.setPassword(password); // 根据自己的情况, 设置password

            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
            prop.put("mail.smtp.timeout", "25000");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("defaultEncoding", "UTF-8");
            senderImpl.setJavaMailProperties(prop);
            // 发送邮件
            senderImpl.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("邮件发送失败");
        }

    }
}
