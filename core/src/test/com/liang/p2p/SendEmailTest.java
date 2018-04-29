package com.liang.p2p;

import com.liang.p2p.base.service.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liang on 2018/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SendEmailTest {

    @Autowired
    private IMailService mailService;


    @Test
    public void testEmail() throws Exception {

        String content = "点击<a href='xxx.do'>这里</a>完成邮箱验证";
        mailService.sendMail("360691676@qq.com", "验证邮件", content);
    }
}
