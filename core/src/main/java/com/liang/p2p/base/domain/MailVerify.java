package com.liang.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 邮箱验证对象
 * Created by liang on 2018/4/26.
 */
@Setter@Getter
public class MailVerify extends BaseDomain {
    private Long userinfoId; //谁在绑定邮箱
    private String email;
    private String uuid;
    private Date sendDate; //发送邮件的时间

}
