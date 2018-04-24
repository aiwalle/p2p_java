package com.liang.p2p.base.domain;

import com.liang.p2p.base.util.BidConst;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 用户对应的账户信息
 * Created by liang on 2018/4/23.
 */
@Setter@Getter
public class Account extends BaseDomain {

    private int version;
    private String tradePassword;
    private BigDecimal usableAmount= BidConst.ZERO;
    private BigDecimal freezedAmount= BidConst.ZERO;
    private BigDecimal unReceiveInterest= BidConst.ZERO;
    private BigDecimal unReceivePrincipal= BidConst.ZERO;
    private BigDecimal unReturnAmount= BidConst.ZERO;
    private BigDecimal remainBorrowLimit= BidConst.INIT_BORROW_LIMIT;
    private BigDecimal borrowLimit= BidConst.INIT_BORROW_LIMIT;

    public BigDecimal getTotalAmount() {
        return usableAmount.add(freezedAmount).add(unReceivePrincipal);
    }
}
