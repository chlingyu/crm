package com.lingyu.login.service;

import com.lingyu.login.model.Activity;
import com.lingyu.login.model.User;
import com.lingyu.login.model.UserAndInfo;
import com.lingyu.login.model.UserAndInfoAndPartner;

import java.util.List;

public interface AdminService {
    UserAndInfo getUserJoinInfo(String uid);

    List<Activity> getActivityByActCity(String city);

    List<UserAndInfoAndPartner> getRecommend();
}
