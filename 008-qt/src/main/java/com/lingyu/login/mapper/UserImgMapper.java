package com.lingyu.login.mapper;

import com.lingyu.login.model.UserImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserImgMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(UserImg record);

    int insertSelective(UserImg record);

    UserImg selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(UserImg record);

    int updateByPrimaryKey(UserImg record);

    List<UserImg> getUserImgByUid(String user);

    int insertUserImgByUid(Integer uid, String fileName);
}