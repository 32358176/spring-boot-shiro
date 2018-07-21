package com.gov.xmxx.system.jwt;

import com.gov.xmxx.system.exception.RoleException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hanyong
 * shiro 核心拦截器
 */

public class MyShiroFilter extends BasicHttpAuthenticationFilter {

    /**
     * The Basic authentication filter can be configured with a list of HTTP methods to which it should apply. This
     * method ensures that authentication is <em>only</em> required for those HTTP methods specified. For example,
     * if you had the configuration:
     * <pre>
     *    [urls]
     *    /basic/** = authcBasic[POST,PUT,DELETE]
     *    /sad=logout
     * </pre>
     * then a GET request would not required authentication but a POST would.
     *
     * @param request     The current HTTP servlet request.
     * @param response    The current HTTP servlet response.
     * @param mappedValue The array of configured HTTP methods as strings. This is empty if no methods are configured.
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                throw new RoleException(401,"登录异常");
            }
        }
        return true;
    }

    /**
     * @throws Exception if an error occurs
     */
    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization,SessionToken");
        response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}