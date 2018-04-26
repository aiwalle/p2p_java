package com.liang.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 登陆日志
 * Created by liang on 2018/4/24.
 */
@Setter
@Getter
public class Iplog extends BaseDomain {

    public static final int STATE_SUCCESS = 1;
    public static final int STATE_FAILED = 0;

    private String ip;
    private Date loginTime;
    private String userName;
    private int state;

    private int userType; // 用户类型，前端用户还是后端用户

    public String getStateDisplay() {
        return state == STATE_SUCCESS ? "登录成功" : "登录失败";
    }

    public String getUserTypeDisplay() {
        return userType == Logininfo.USER_CLIENT ? "前端用户" : "后台管理员";
    }
}
