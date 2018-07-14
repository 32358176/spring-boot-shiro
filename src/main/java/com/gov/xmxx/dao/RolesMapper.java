package com.gov.xmxx.dao;

import com.gov.xmxx.pojo.Roles;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);

    Set<String> selectRolesNameByUsername(String username);
}