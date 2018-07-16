package com.gov.xmxx.system.asp;


import com.google.gson.Gson;
import com.gov.xmxx.dao.LogMessageMapper;
import com.gov.xmxx.dao.RolesMapper;
import com.gov.xmxx.dao.UsersMapper;
import com.gov.xmxx.pojo.LogMessage;
import com.gov.xmxx.pojo.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Aspect
@Component
public class LogAsp {

    @Autowired
    private LogMessageMapper logMessageMapper;

    @Autowired
    private RolesMapper rolesMapper;

//    @Pointcut("execution(public * com.gov.xmxx.service..*(..))")
//    public void pointCut() {
//        // SERVICE层切入点
//    }



    @Around("@annotation(com.gov.xmxx.system.asp.SystemLog)")
    public Object show(ProceedingJoinPoint joinPoint) {
        LogMessage logMessage = new LogMessage();
        Class clazz = joinPoint.getTarget().getClass();
        Object[] objects = joinPoint.getArgs();
        Gson gson = new Gson();
        String argus = "";
        String methodName = joinPoint.getSignature().getName();
        Method[] methods = clazz.getMethods();
        Object object = null;
        Long startTime = System.currentTimeMillis();
        String startNow = timeNow();
        for (Method m : methods) {
            if (methodName.equals(m.getName())) {
                if (objects.length == m.getParameterTypes().length) {
                    if (m.getAnnotation(SystemLog.class) != null) {
                        if (m.getAnnotation(SystemLog.class).isWrite()) {
                            argus = gson.toJson(objects);
                        } else {
                            for (int i = 0; i < objects.length; i++) {
                                argus += objects[i].getClass().getTypeName() + ",";
                            }
                        }
                        logMessage.setStartdatetime(startNow);
                        logMessage.setMethodname(methodName);
                        logMessage.setDesciption(m.getAnnotation(SystemLog.class).description());
                        try {
                            object = joinPoint.proceed(objects);
                            logMessage.setStutas("成功");
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                            logMessage.setStutas("失败");
                            logMessage.setException(throwable.toString());
                        }
                        Long endTime = System.currentTimeMillis();
                        String time = endTime - startTime + "ms";
                        String endNow = timeNow();
                        logMessage.setArgus(argus);
                        logMessage.setEnddatetime(endNow);
                        logMessage.setTime(time);
                        Users users = (Users)SecurityUtils.getSubject().getPrincipal();
                        String username = "";
                        Set<String> roles = new HashSet<>();
                        if(users != null){
                            username = users.getUsername();
                            roles = rolesMapper.selectRolesNameByUsername(username);
                        }

                        logMessage.setUsername(username);
                        logMessage.setRoles(roles.toString());
                        logMessageMapper.insertSelective(logMessage);
                    }

                }
            }

        }
        return object;
    }

    private static String timeNow() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

}
