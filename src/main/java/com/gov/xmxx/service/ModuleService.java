package com.gov.xmxx.service;

import com.gov.xmxx.dao.ModulesMapper;
import com.gov.xmxx.pojo.Modules;
import com.gov.xmxx.system.asp.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hanyong
 * @date 2018/7/17 10:11
 */

@Service
public class ModuleService {

    @Autowired
    private ModulesMapper modulesMapper;

    @SystemLog(description = "查询用户拥有模块")
    public List<Modules> selectModulesByUserId(Integer userId) {
        return modulesMapper.selectModulesByUserId(userId);
    }
}
