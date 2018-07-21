package com.gov.xmxx.view;

import com.gov.xmxx.pojo.Page;
import com.gov.xmxx.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author hanyong
 * @date 2018/7/16 17:16
 * 权限模块
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/permission",name = "权限模块")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping(value = "/queryAll",name = "查询所有权限")
//    @RequiresRoles("超级管理员")
    public Page queryAllPermissions(Integer page,Integer limit){
        return permissionService.queryAllPermissions(page,limit);
    }

    @GetMapping(value = "/updatePermission",name = "更新系统权限")
//    @RequiresRoles("超级管理员")
    public String updatePermission(){
        return permissionService.updatePermission();
    }
}
