package com.gov.xmxx.dao;

import com.gov.xmxx.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    List<Users> queryAllUser(Users users);

    Users selectUserByUsername(String username);

    Integer updateLastTimeAndLastIp(@Param("id") Integer id, @Param("time") String time, @Param("ip") String ip);
}