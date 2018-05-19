package com.liang.p2p.business.service;

import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.business.domain.BidRequest;
import com.liang.p2p.business.query.BidRequestQueryObject;

/**
 * 借款相关
 * Created by liang on 2018/5/17.
 */
public interface IBidRequestService {


    void update(BidRequest bidRequest);

    /**
     * 判断用户是否具有申请借款的权利
     * @return
     */
    boolean canApplyBidRequest(Long logininfoId);

    /**
     * 申请借款
     * @param bidRequest
     */
    void apply(BidRequest bidRequest);


    PageResult query(BidRequestQueryObject qo);

    /**
     * 发标前审核
     * @param id
     * @param remark
     * @param state
     */
    void publishAudit(Long id, String remark, int state);
}
