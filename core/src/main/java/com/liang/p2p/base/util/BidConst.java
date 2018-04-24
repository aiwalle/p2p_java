package com.liang.p2p.base.util;

import java.math.BigDecimal;

/**
 * 系统中的常亮都放在这里
 * Created by liang on 2018/4/23.
 */
public class BidConst {
    /**
     * 定义存储精度
     */
    public static final int STORE_SCALE = 4;

    /**
     * 定义运算精度
     */
    public static final int CAL_SCALE = 8;

    /**
     * 定义显示精度
     */
    public static final int DISPLAY_SCALE = 2;

    /**
     * 定义系统级别的0
     */
    public static final BigDecimal ZERO = new BigDecimal("0.0000");

    /**
     * 定义初始授信额度
     */
    public static final BigDecimal INIT_BORROW_LIMIT = new BigDecimal("5000.0000");

}
