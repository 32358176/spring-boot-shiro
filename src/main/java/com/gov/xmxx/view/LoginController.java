package com.gov.xmxx.view;


import com.gov.xmxx.pojo.Page;
import com.gov.xmxx.pojo.Users;
import com.gov.xmxx.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/confirm")
    public Map loginConfrim(@Valid Users users , BindingResult result) throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<>();
        boolean error = result.hasErrors();
        if(error){
            String message = result.getAllErrors().get(0).getDefaultMessage();

            map.put("200",message);
            return map;
        }
        return loginService.loginConfirm(users);
    }

    @GetMapping("/logout")
    public Map<String,Object> logout(){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            resultMap.put("204","登出失败!");
        }
        resultMap.put("200","登出成功!");
        return resultMap;
    }
}
