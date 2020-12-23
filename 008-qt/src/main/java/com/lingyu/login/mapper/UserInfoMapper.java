package com.lingyu.login.mapper;

import com.lingyu.login.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer infoid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer infoid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int insertUserInfo(Integer uid, String sex);

    UserInfo getUserInfoByUid(String user);


    int modifyUserInfo(UserInfo userInfo);

    int insertUserInfoById(UserInfo userInfo);
}