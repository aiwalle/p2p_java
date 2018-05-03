package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.RealAuth;
import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.mapper.RealAuthMapper;
import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.query.RealAuthQueryObject;
import com.liang.p2p.base.service.IRealAuthService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.BitStatesUtils;
import com.liang.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liang on 2018/4/30.
 */
@Service
public class RealAuthServiceImpl implements IRealAuthService {

    @Autowired
    private RealAuthMapper realAuthMapper;

    @Autowired
    private IUserinfoService userinfoService;

    public RealAuth get(Long id) {
        return realAuthMapper.selectByPrimaryKey(id);
    }

    public void apply(RealAuth realAuth) {
        Userinfo current = userinfoService.getCurrent();
        // 判断当前用户没有实名认证，且当前用户不处于待审核状态
        if (!current.getIsRealAuth() && current.getRealAuthId() == null) {
            // 保存一个实名认证对象
            realAuth.setState(RealAuth.STATE_NORMAL);
            realAuth.setApplier(UserContext.getCurrent());
            realAuth.setApplyTime(new Date());
//            realAuth.setAuditor(null);
//            realAuth.setApplyTime(null);
            realAuthMapper.insert(realAuth);
            // 把实名认证的id设置给userinfo
            current.setRealAuthId(realAuth.getId());
            userinfoService.update(current);
        }

    }

    public PageResult query(RealAuthQueryObject queryObject) {
        int count = realAuthMapper.queryForCount(queryObject);
        if (count > 0) {
            List<RealAuth> list = realAuthMapper.query(queryObject);
            return new PageResult(list, count, queryObject.getCurrentPage(), queryObject.getPageSize());
        }
        return PageResult.empty(queryObject.getPageSize());
    }

    public void audit(Long id, String remark, int state) {
        // 根据id得到实名认证的对象
        RealAuth realAuth = get(id);
        // 如果对象存在，并且对象处于待审核状态
        if (realAuth != null && realAuth.getState() == RealAuth.STATE_NORMAL) {
            // 1.设置通用属性
            realAuth.setAuditor(UserContext.getCurrent());
            realAuth.setApplyTime(new Date());
            realAuth.setState(state);
            // 2.如果状态是审核拒绝
            Userinfo applier = userinfoService.get(realAuth.getApplier().getId());
            if (state == RealAuth.STATE_AUDIT) {
                // 3.如果状态是审核通过
                //1.保证用户处于未审核状态
                //2.添加审核的状态码，设置userinfo上的冗余数据，重新realauthid
                if (!applier.getIsRealAuth()) {
                    applier.addState(BitStatesUtils.OP_REAL_AUTH);
                    applier.setRealName(realAuth.getRealName());
                    applier.setIdNumber(realAuth.getIdNumber());
                    applier.setRealAuthId(realAuth.getId());

                }
            } else  {
                //1.userinfo中的realauthid设置为空
                applier.setRealAuthId(null);
            }
            userinfoService.update(applier);
            realAuthMapper.updateByPrimaryKey(realAuth);

        }
    }


}
