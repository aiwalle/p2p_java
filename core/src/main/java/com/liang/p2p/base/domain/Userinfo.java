package com.liang.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户相关信息
 * Created by liang on 2018/4/23.
 */
@Getter@Setter
public class Userinfo extends BaseDomain {
    private int version;    // 版本号
    private long bitState = 0;  // 用户状态码
    private String realName;
    private String idNumber;
    private String phoneNumber;
    private SystemDictionaryItem incomeGrade;  // 收入等级
    private SystemDictionaryItem marriage;     // 婚姻状况
    private SystemDictionaryItem kidCount;     // 小孩个数
    private SystemDictionaryItem educationBackground;  // 教育背景
    private SystemDictionaryItem houseCondition;       // 住房条件
}
