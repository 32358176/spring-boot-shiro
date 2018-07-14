package com.gov.xmxx.dao;

import com.gov.xmxx.pojo.Modules;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ModulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Modules record);

    int insertSelective(Modules record);

    Modules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Modules record);

    int updateByPrimaryKey(Modules record);

    Set<String> selectModulesNameByUserId(Integer id);
}