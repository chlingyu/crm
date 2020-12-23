package com.lingyu.login.service.impl;

import com.lingyu.login.mapper.UserInfoMapper;
import com.lingyu.login.mapper.UserMapper;
import com.lingyu.login.model.User;
import com.lingyu.login.service.LoginService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<User> getUser() {
        List<User> userList=userMapper.getUser();
        return userList;
    }

    @Override
    public User getUserByPhone(String phone) {
        User user=userMapper.getUserByPhone(phone);
        return user;
    }

    @Override
    public int insertUser(User insertUser) {
        int count=userMapper.insertUser(insertUser);
        return count;
    }

    @Override
    public User getUserByPhoneAndPwd(String phone, String pwd) {
        User user=userMapper.getUserByPhoneAndPwd(phone,pwd);
        return user;
    }

    @Override
    public int modifyResetPwd(String phone) {
        int count=userMapper.updateResetPwdByPhone(phone);
        return count;
    }

    @Override
    public int insertUserInfo(Integer uid, String sex) {
        int count=userInfoMapper.insertUserInfo(uid,sex);
        return count;
    }
}
