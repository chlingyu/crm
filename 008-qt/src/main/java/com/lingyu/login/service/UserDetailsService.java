package com.lingyu.login.service;

import com.lingyu.login.model.*;

import java.util.List;

public interface UserDetailsService {
    Visitors getVisitorsByfUidAndtUid(String fUid, String user);

    int insertNewVisitors(String fUid, String user);

    int delete(String fUid, String user);

    UserInfo getUserInfoByUid(String user);

    List<UserImg> getUserImgByUid(String user);

    User getUserByUid(String user);

    List<UserHobby> getHobbyByUid(String user);

    List<UserTag> getTagByUid(String user);

    UserPartner getPartnerByUid(String user);

    List<Gift> getGift();

    UserFocus getFocusByfUidAndUId(String fUid, String user);
}
