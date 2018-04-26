package com.liang.p2p.base.util;

import com.liang.p2p.base.domain.Logininfo;
import com.liang.p2p.base.vo.VerifyCodeVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 用户存放当前用户的上下文
 * Created by liang on 2018/4/23.
 */
public class UserContext {
    public static final String USER_IN_SESSION = "logininfo";

    public static final String VERIFYCODE_IN_SESSION = "verifycode_in_session";

    /**
     * 这是一个反向的获取request的方法，请查看RequestContextHolder的requestInitialized的打包过程
     * 这里有一个配置是在web.xml中
     * <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
     * @return
     */
    private static HttpSession getSession() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    public static void putCurrent(Logininfo logininfo) {
        // 得到session，并把current放到session中
        getSession().setAttribute(USER_IN_SESSION, logininfo);
    }

    public static Logininfo getCurrent() {
        // 得到session，获取logininfo
        return (Logininfo) getSession().getAttribute(USER_IN_SESSION);
    }

    public static void putVerifyCode(VerifyCodeVO vo) {
        getSession().setAttribute(VERIFYCODE_IN_SESSION, vo);
    }

    public static VerifyCodeVO getCurrentVerifyCode() {
        return (VerifyCodeVO) getSession().getAttribute(VERIFYCODE_IN_SESSION);
    }



}
