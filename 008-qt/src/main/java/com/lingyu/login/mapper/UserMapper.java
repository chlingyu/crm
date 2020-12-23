package com.lingyu.login.mapper;

import com.lingyu.login.model.User;
import java.util.Date;
import java.util.List;

import com.lingyu.login.model.UserAndInfo;
import com.lingyu.login.model.UserAndInfoAndPartner;
import com.lingyu.login.model.UserHobby;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("addtime") Date addtime);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("uid") Integer uid, @Param("addtime") Date addtime);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> getUser();

    User getUserByPhone(String phone);

    int insertUser(User insertUser);

    User getUserByPhoneAndPwd(String phone, String pwd);

    int updateResetPwdByPhone(String phone);

    UserAndInfo getUserJoinInfo(String uid);

    List<UserAndInfoAndPartner> getRecommend();

    User getUserByUid(String user);


    int updateUnameByUid(String uid, String uname);

    int updateUseravatarByUid(Integer uid, String fileName);


    int updateMoneyByUid(Integer uid, float money);


}