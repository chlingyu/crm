package com.lingyu.login.service.impl;

import com.lingyu.login.mapper.*;
import com.lingyu.login.model.*;
import com.lingyu.login.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private VisitorsMapper visitorsMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserImgMapper userImgMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserHobbyMapper userHobbyMapper;
    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private UserPartnerMapper userPartnerMapper;
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private UserFocusMapper userFocusMapper;
    @Override
    public Visitors getVisitorsByfUidAndtUid(String fUid, String user) {
        Visitors visitors=visitorsMapper.getVisitorsByfUidAndtUid(fUid,user);
        return visitors;
    }

    @Override
    public int insertNewVisitors(String fUid, String user) {
        int count=visitorsMapper.insertNewVisitors(fUid,user);
        return count;
    }

    @Override
    public int delete(String fUid, String user) {
        int count=visitorsMapper.deleteByfUidAndtUid(fUid,user);
        return count;
    }

    @Override
    public UserInfo getUserInfoByUid(String user) {
        UserInfo userInfo=userInfoMapper.getUserInfoByUid(user);
        return userInfo;
    }

    @Override
    public  List<UserImg> getUserImgByUid(String user) {
        List<UserImg> userImg=userImgMapper.getUserImgByUid(user);
        return userImg;
    }

    @Override
    public User getUserByUid(String user) {
        User userBy=userMapper.getUserByUid(user);
        return userBy;
    }

    @Override
    public List<UserHobby> getHobbyByUid(String user) {
        List<UserHobby> userHobbyList=userHobbyMapper.getHobbyByUid(user);
        return userHobbyList;
    }

    @Override
    public List<UserTag> getTagByUid(String user) {
        List<UserTag> userTags=userTagMapper.getTagByUid(user);
        return userTags;
    }

    @Override
    public UserPartner getPartnerByUid(String user) {
        UserPartner userPartner=userPartnerMapper.getPartnerByUid(user);
        return userPartner;
    }

    @Override
    public List<Gift> getGift() {
        List<Gift> gifts=giftMapper.getGift();
        return gifts;
    }

    @Override
    public UserFocus getFocusByfUidAndUId(String fUid, String user) {
        UserFocus userFocus=userFocusMapper.getFocusByfUidAndUId(fUid,user);
        return userFocus;
    }


}
