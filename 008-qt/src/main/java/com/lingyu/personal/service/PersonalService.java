package com.lingyu.personal.service;

import com.lingyu.login.model.*;

import java.util.List;

public interface PersonalService {
    UserInfo getUserInfoByUid(String uid);

    int modifyUnameByUid(String uid, String uname);


    int modifyUserInfo(UserInfo userInfo);

    int addUserInfo(UserInfo userInfo);

    List<UserTag> getUserTagByUid(String uid);

    HobbyTag getHobbyTagByTid(Integer tid);

    List<HobbyTag> getHobbyTag();

    int deleteUserTagByUId(Integer uid);

    int insertUserByUidAndArray(Integer[] a, Integer uid);

    List<UserImg> getUserImgByUid(String uid);

    int insertUserImgByUid(Integer uid, String fileName);

    int updateUseravatarByUid(Integer uid, String fileName);

    User getUserByUid(String uid);


    int modifyUserMoneyByUid(Integer uid, float money);
}
