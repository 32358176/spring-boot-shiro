package com.gov.xmxx.view;

import com.gov.xmxx.pojo.Page;
import com.gov.xmxx.pojo.Users;
import com.gov.xmxx.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/user",name = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/queryAllUsers",name = "查询所有用户")
    public Page queryAllUsers(Integer page,Integer limit){
        return userService.queryAllUsers(page,limit);
    }

    @PostMapping(value = "/regist",name = "注册账号")
    public Page regist(Users users){
        return userService.regist(users);
    }
    /**
     * 用户必须登录
     */
    @GetMapping("/require_auth")
    @RequiresAuthentication
    public Page requireAuth() {
        return new Page(200, "你已登陆");
    }

    @GetMapping("/require_role")
    @RequiresRoles("超级管理员")
    public Page requireRole() {
        return new Page(200, "你拥有访问角色");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.OR, value = {"修改", "删除"})
    public Page requirePermission() {
        return new Page(200, "你拥有修改，删除权限");
    }

}
