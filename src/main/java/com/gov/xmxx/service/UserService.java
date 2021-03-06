package com.gov.xmxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gov.xmxx.dao.UsersMapper;
import com.gov.xmxx.pojo.Page;
import com.gov.xmxx.pojo.Users;
import com.gov.xmxx.system.asp.LogAsp;
import com.gov.xmxx.system.jwt.SystemCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private SystemCredentials systemCredentials;
    public Page queryAllUsers(Integer page,Integer limit,Users user){

        PageHelper.startPage(page,limit);
        List<Users> users = usersMapper.queryAllUser(user);
        return new Page(new PageInfo(users));
    }



    public Page regist(Users users) {
        Users user = usersMapper.selectUserByUsername(users.getUsername());
        if(user != null){
            return new Page(206,"用户名已存在");
        }
        String password = systemCredentials.sysCredentials(users);
        users.setIslockout("否");
        users.setCreatetime(LogAsp.timeNow());
        users.setPassword(password);
        Integer n = usersMapper.insertSelective(users);
        if(n != 0){
            return new Page(200,"添加成功");
        }
        return new Page(204,"添加失败");
    }
}
