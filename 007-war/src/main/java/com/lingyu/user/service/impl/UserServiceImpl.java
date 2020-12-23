package com.lingyu.user.service.impl;

import com.lingyu.user.mapper.UserMapper;
import com.lingyu.user.model.User;
import com.lingyu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUser(Integer start, Integer per_page) {
        List<User> userList=userMapper.getUser(start,per_page);
        return userList;
    }

    @Override
    public List<User> getUserBy(String p_keyword, Integer start, Integer per_page) {
        List<User> userList=userMapper.getUserBy(p_keyword,start,per_page);
        return userList;
    }

    @Override
    public Integer getTotal(String p_keyword) {
        Integer count=userMapper.getTotal(p_keyword);
        return count;
    }

    @Override
    public Integer count() {
        Integer count=userMapper.getAccount();
        return count;
    }

    @Override
    @Transactional
    public boolean modifyStatusLock(Integer uid) {
        int count=userMapper.modifyStatusLock(uid);
        if(count!=1){
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyStatusOpen(Integer uid) {
        int count=userMapper.modifyStatusOpen(uid);
        if(count!=1){
            return false;
        }
        return true;
    }

    @Override
    public int modifyPwd(Integer uid) {
        int count=userMapper.modifyPwd(uid);
        return count;
    }
}
