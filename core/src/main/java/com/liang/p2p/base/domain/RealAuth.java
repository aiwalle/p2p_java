package com.liang.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by liang on 2018/4/29.
 */
@Setter@Getter
public class RealAuth extends BaseDomain {


    public static final int SEX_MALE = 0;
    public static final int SEX_FEMALE = 1;

    public static final int STATE_NORMAL = 0;   // 正常
    public static final int STATE_AUDIT = 1;    // 审核通过
    public static final int STATE_REJECT = 2;   // 拒绝



    private String realName;
    private int sex;
    private String idNumber; //证件号码
    private String bornDate; // 出生日期
    private String address;
    private String image1;  // 身份证正面图片地址
    private String image2;  // 身份证背面图片地址

    private int state;  // 状态
    private Logininfo applier; // 申请人
    private Logininfo auditor; // 审核人
    private String remark; // 审核备注
    private Date applyTime;  // 申请时间
    private Date auditTime;  // 审核时间



}
