package com.liang.p2p.base.service;

import com.liang.p2p.base.domain.Userinfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liang on 2018/4/24.
 */
public interface IUserinfoService {

    /**
     * 记住，写完mapper以后立刻写service，因为这个update是支持乐观锁的
     * @param userinfo
     */
    void update(Userinfo userinfo);


    /**
     * 添加userinfo
     * @param userinfo
     */
    void add(Userinfo userinfo);

    Userinfo get(Long id);

    /**
     * 绑定手机号
     * @param phoneNumber
     * @param verifyCode
     */
    void bindPhone(String phoneNumber, String verifyCode);


    /**
     * 发送绑定邮箱邮件
     * @param email
     */
    void sendVerifyEmail(String email);


    /**
     * 直接得到当前对象
     * @return
     */
    Userinfo getCurrent();

    /**
     * 执行邮箱绑定
     * @param uuid
     */
    void bindEmail(String uuid);

    /**
     * 只是用于更新用户基本数据，这个方法可以防止从前台往后台传递数据的时候，数据的丢失
     * @param userinfo
     */
    void updateBasicInfo(Userinfo userinfo);


    /**
     * 用于用户的autoComplate
     * 返回的Map：{id:logininfoId,username:username}
     * @param keyword
     * @return
     */
    List<Map<String,Object>> autoComplate(String keyword);
}
