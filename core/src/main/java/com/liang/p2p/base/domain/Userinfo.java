package com.liang.p2p.base.domain;

import com.liang.p2p.base.util.BitStatesUtils;
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
    private String email;
    private int score; //风控累积分数

    private Long realAuthId;   // 该用户对应的实名认证对象id

    private SystemDictionaryItem incomeGrade;  // 收入等级
    private SystemDictionaryItem marriage;     // 婚姻状况
    private SystemDictionaryItem kidCount;     // 小孩个数
    private SystemDictionaryItem educationBackground;  // 教育背景
    private SystemDictionaryItem houseCondition;       // 住房条件



    /**
     * 添加状态码
     *
     * @param state
     */
    public void addState(long state) {
        this.setBitState(BitStatesUtils.addState(this.bitState, state));
    }

    public void removeState(long state) {
        this.setBitState(BitStatesUtils.removeState(this.bitState, state));
    }

    /**
     * 返回用户是否已经绑定手机
     *
     * @return
     */
    public boolean getIsBindPhone() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_PHONE);
    }

    /**
     * 返回用户是否已经绑定邮箱
     *
     * @return
     */
    public boolean getIsBindEmail() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_EMAIL);
    }

    /**
     * 返回用户是否已经填写了基本资料
     *
     * @return
     */
    public boolean getIsBasicInfo() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BASIC_INFO);
    }

    /**
     * 返回用户是否已经实名认证
     *
     * @return
     */
    public boolean getIsRealAuth() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_REAL_AUTH);
    }

    /**
     * 返回用户是否视频认证
     *
     * @return
     */
    public boolean getIsVedioAuth() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_VEDIO_AUTH);
    }

    /**
     * 返回用户是否绑定银行卡
     *
     * @return
     */
    public boolean getIsBindBank() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_BANKINFO);
    }

    /**
     * 返回用户是否有一个借款在处理流程当中
     *
     * @return
     */
    public boolean getHasBidRequestProcess() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
    }

    /**
     * 返回用户是否有一个提现申请在处理流程当中
     *
     * @return
     */
    public boolean getHasWithdrawProcess() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_HAS_MONEYWITHDRAW_PROCESS);
    }
}
