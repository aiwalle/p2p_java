package com.liang.mgrsite.listener;

import com.liang.p2p.base.service.ILogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 初始化超级管理员的监听器
 * 1.在Spring中，实现了ApplicationListener接口的类就可以作为Spring的监听器来监听Spring中的特殊事件
 * 2.在Spring里面，ApplicationEvent这个类相当等于所有的事件，如果我们的类继承的是ApplicationListener<ApplicationEvent>；
 * 相当于我们这个监听器监听的是Spring中所有的消息
 * 3.现在我只想监听Spring容器启动完成的事件，只需要监听ContextRefreshedEvent
 *
 * Created by liang on 2018/4/25.
 */
@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ILogininfoService logininfoService;

    /**
     * 这个方法就是监听到指定的事件之后，要做的事情。
     * @param applicationEvent    就是这次监听到的事件
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
//        System.out.println("Spring启动好了");
        // 在这里来创建第一个管理员
        logininfoService.initAdmin();

    }

}
