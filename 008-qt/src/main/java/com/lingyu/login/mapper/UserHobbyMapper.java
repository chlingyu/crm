package com.lingyu.login.mapper;

import com.lingyu.login.model.UserHobby;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserHobbyMapper {
    int deleteByPrimaryKey(Integer hid);

    int insert(UserHobby record);

    int insertSelective(UserHobby record);

    UserHobby selectByPrimaryKey(Integer hid);

    int updateByPrimaryKeySelective(UserHobby record);

    int updateByPrimaryKey(UserHobby record);


    List<UserHobby> getHobbyByUid(String user);
}