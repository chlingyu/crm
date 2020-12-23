package com.lingyu.login.mapper;

import com.lingyu.login.model.UserTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTag record);

    int insertSelective(UserTag record);

    UserTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTag record);

    int updateByPrimaryKey(UserTag record);

    List<UserTag> getTagByUid(String user);

    int deleteByUid(Integer uid);

    int insertUserByUidAndArray(@Param("a")Integer[] a, @Param("uid")Integer uid);
}