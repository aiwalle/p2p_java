package com.liang.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter@Getter
public class Logininfo extends BaseDomain {

    public static final int STATE_NORMAL = 0; // 正常
    public static final int STATE_LOCK = 1; // 锁定

    private String username;

    private String password;

    private int state;

}