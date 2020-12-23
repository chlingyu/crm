package com.lingyu.login.service;

import com.lingyu.login.model.User;

import java.util.List;

public interface LoginService {


    List<User> getUser();

    User getUserByPhone(String phone);

    int insertUser(User insertUser);


    User getUserByPhoneAndPwd(String phone, String pwd);

    int modifyResetPwd(String phone);

    int insertUserInfo(Integer uid, String sex);
}
