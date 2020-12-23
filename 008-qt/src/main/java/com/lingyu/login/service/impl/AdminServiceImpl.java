package com.lingyu.login.service.impl;

import com.lingyu.login.mapper.ActivityMapper;
import com.lingyu.login.mapper.UserMapper;
import com.lingyu.login.model.Activity;
import com.lingyu.login.model.User;
import com.lingyu.login.model.UserAndInfo;
import com.lingyu.login.model.UserAndInfoAndPartner;
import com.lingyu.login.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public UserAndInfo getUserJoinInfo(String uid) {
        UserAndInfo user=userMapper.getUserJoinInfo(uid);
        return user;
    }

    @Override
    public List<Activity> getActivityByActCity(String city) {
        List<Activity> activityList=activityMapper.getActivityByActCity(city);
        return activityList;
    }

    @Override
    public List<UserAndInfoAndPartner> getRecommend() {
        List<UserAndInfoAndPartner> userAndInfoAndPartnerList=userMapper.getRecommend();
        return userAndInfoAndPartnerList;
    }
}
