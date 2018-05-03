package com.liang.p2p.base.service;

import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.query.VedioAuthQueryObject;

/**
 * 视频认证服务
 * Created by liang on 2018/5/3.
 */
public interface IVedioAuthService  {

    PageResult query(VedioAuthQueryObject qo);

    /**
     * 视频审核逻辑
     * @param loginInfoValue
     * @param remark
     * @param state
     */
    void audit(Long loginInfoValue, String remark, int state);
}
