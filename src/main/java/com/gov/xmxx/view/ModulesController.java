package com.gov.xmxx.view;

import com.gov.xmxx.pojo.Modules;
import com.gov.xmxx.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hanyong
 * @date 2018/7/17 10:01
 * 模块系统
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/modules",name = "模块系统")
public class ModulesController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping(value = "/selectModulesByUserId",name = "根据用户ID查询拥有模块")
    public List<Modules> selectModulesByUserId(Integer userId){

        return moduleService.selectModulesByUserId(userId);
    }
}
