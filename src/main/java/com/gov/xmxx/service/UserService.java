package com.gov.xmxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gov.xmxx.dao.UsersMapper;
import com.gov.xmxx.pojo.Page;
import com.gov.xmxx.pojo.Users;
import com.gov.xmxx.system.asp.LogAsp;
import com.gov.xmxx.system.jwt.SystemCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private SystemCredentials systemCredentials;
    public Page queryAllUsers(Integer page,Integer limit){
        PageHelper.startPage(page,limit);
        List<Users> users = usersMapper.queryAllUser();
        return new Page(new PageInfo(users));
    }



    public Page regist(Users users) {
        String password = systemCredentials.sysCredentials(users);
        users.setCreatetime(LogAsp.timeNow());
        users.setPassword(password);
        Integer n = usersMapper.insertSelective(users);
        if(n != 0){
            return new Page(200,"添加成功");
        }
        return new Page(204,"添加失败");
    }
}
