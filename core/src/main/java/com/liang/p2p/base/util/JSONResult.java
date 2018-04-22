package com.liang.p2p.base.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liang on 2018/4/17.
 */
@Getter@Setter
public class JSONResult {
    private boolean success = true;
    private String msg;

    public JSONResult() {
    }

    public JSONResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JSONResult(String msg) {
        this.msg = msg;
    }
}
