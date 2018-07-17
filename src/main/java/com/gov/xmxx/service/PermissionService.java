package com.gov.xmxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gov.xmxx.dao.PermissionsMapper;
import com.gov.xmxx.pojo.Page;
import com.gov.xmxx.pojo.Permissions;
import com.gov.xmxx.system.asp.LogAsp;
import com.gov.xmxx.system.asp.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * @author hanyong
 * @date 2018/7/16 17:16
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @SystemLog(description = "查询所有权限")
    public Page queryAllPermissions(Integer page, Integer limit) {
        try {
            PageHelper.startPage(page, limit);
        } catch (Exception e) {
            return new Page(204,"分页参数传递错误！");
        }
        List<Permissions> permissions = permissionsMapper.queryAllPermissions();
        return new Page(new PageInfo(permissions));

    }

    @SystemLog(description = "更新系统权限")
    public String updatePermission() {
        Integer n = 0;
        //获取当前已存在的所有的权限
        List<String> permissionList = permissionsMapper.queryAllPermissionsValue();
        //创建权限集合
        List<Permissions> permissions = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodMap =
                requestMappingHandlerMapping.getHandlerMethods();
        //得到所有被requestMapping所修饰的方法
        Collection<HandlerMethod> handlerMethods = mappingInfoHandlerMethodMap.values();
        if (handlerMethods == null || handlerMethods.isEmpty()) {
            n = 0;
        }
        for (HandlerMethod handlerMethod : handlerMethods) {
            Permissions permissiontb = new Permissions();
            RequestMapping methodRequestMapping =
                    handlerMethod.getMethodAnnotation(RequestMapping.class);
            if (!methodRequestMapping.name().equals((""))) {
                String methodURL = methodRequestMapping.value()[0];
                RequestMapping classRequestMapping =
                        handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
                String classURL = classRequestMapping.value()[0];
                String module = "".equals(classRequestMapping.name()) ? "" : classRequestMapping.name();
                String permissionURL = (classURL + ":" + methodURL).replace("/", "");
                if (permissionList != null) {
                    if (permissionList.contains(permissionURL)) {
                        continue;
                    }
                }
                permissiontb.setPermissionname(methodRequestMapping.name());
                permissiontb.setPermissionvalue(permissionURL);
                permissiontb.setPermissionmodule(module);
                permissiontb.setPermissionlastupdatetime(LogAsp.timeNow());
                permissions.add(permissiontb);
            }
        }
        if (permissions.size() != 0) {
            n = permissionsMapper.insertSystemPermission(permissions);
        } else {
            n = 0;
        }
        return "更新了" + n + "条系统权限!";

    }
}
