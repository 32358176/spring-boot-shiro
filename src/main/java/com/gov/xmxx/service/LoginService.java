package com.gov.xmxx.service;


import com.gov.xmxx.pojo.Users;
import com.gov.xmxx.system.asp.SystemLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class LoginService {


    @SystemLog(description = "登陆操作")
    public Map loginConfirm(Users users) {
        Map<String, Object> map = new HashMap<>();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());

        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            // rememberme
            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);
                map.put("resultCode",200);
                map.put("resultMessage", "登陆成功");
                map.put("resultData",users);
            }
            // 所有认证时异常的父类.
            catch (IncorrectCredentialsException e) {
                map.put("resultCode",204);
                map.put("resultMessage", "密码错误");
            } catch (LockedAccountException e) {
                map.put("resultCode",205);
                map.put("resultMessage", "登陆失败，用户被冻结");
            } catch (AuthenticationException e) {
                map.put("resultCode",206);
                map.put("resultMessage", "用户不存在");
            }
        }
        return map;
    }
}
