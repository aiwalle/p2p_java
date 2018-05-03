package com.liang.p2p.base.service;

import com.liang.p2p.base.domain.RealAuth;
import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.query.RealAuthQueryObject;

/**
 * 实名认证对象服务
 * Created by liang on 2018/4/30.
 */
public interface IRealAuthService {

    RealAuth get(Long id);

    /**
     * 实名认证申请
     * @param realAuth
     */
    void apply(RealAuth realAuth);


    /**
     * 分页查询
     * @param queryObject
     * @return
     */
    PageResult query(RealAuthQueryObject queryObject);

    /**
     * 实名认证审核
     * @param id
     * @param remark
     * @param state
     */
    void audit(Long id, String remark, int state);
}
