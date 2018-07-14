package com.gov.xmxx.dao;

import com.gov.xmxx.pojo.Permissions;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);

    Set<String> selectPermissionValueByUsername(String username);
}