package com.lingyu.personal.service.serviceImpl;

import com.lingyu.login.mapper.*;
import com.lingyu.login.model.*;
import com.lingyu.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private HobbyTagMapper hobbyTagMapper;
    @Autowired
    private UserImgMapper userImgMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserInfo getUserInfoByUid(String uid) {
        UserInfo userInfo=userInfoMapper.getUserInfoByUid(uid);
        return userInfo;
    }

    @Override
    public int modifyUnameByUid(String uid, String uname) {
       int count= userMapper.updateUnameByUid(uid,uname);
       return count;
    }

    @Override
    public int modifyUserInfo(UserInfo userInfo) {
        int count=userInfoMapper.modifyUserInfo(userInfo);
        return count;
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
       int count= userInfoMapper.insertUserInfoById(userInfo);
       return count;
    }

    @Override
    public List<UserTag> getUserTagByUid(String uid) {
        List<UserTag> userTags=userTagMapper.getTagByUid(uid);
        return userTags;
    }

    @Override
    public HobbyTag getHobbyTagByTid(Integer tid) {
       HobbyTag hobbyTag=hobbyTagMapper.selectByPrimaryKey(tid);
       return hobbyTag;
    }

    @Override
    public List<HobbyTag> getHobbyTag() {
        List<HobbyTag> hobbyTagList=hobbyTagMapper.selectAll();
        return  hobbyTagList;
    }

    @Override
    public int deleteUserTagByUId(Integer uid) {
        int count=userTagMapper.deleteByUid(uid);
        return count;
    }

    @Override
    public int insertUserByUidAndArray(Integer[] a, Integer uid) {
        int count=userTagMapper.insertUserByUidAndArray(a,uid);
        return count;
    }

    @Override
    public List<UserImg> getUserImgByUid(String uid) {
        List<UserImg> userImgs=userImgMapper.getUserImgByUid(uid);
        return userImgs;
    }

    @Override
    public int insertUserImgByUid(Integer uid, String fileName) {
        int count=userImgMapper.insertUserImgByUid(uid,fileName);
        return count;
    }

    @Override
    public int updateUseravatarByUid(Integer uid, String fileName) {
        int count=userMapper.updateUseravatarByUid(uid,fileName);
        return count;
    }

    @Override
    public User getUserByUid(String uid) {
        User user=userMapper.getUserByUid(uid);
        return user;
    }

    @Override
    public int modifyUserMoneyByUid(Integer uid, float money) {
        int count=userMapper.updateMoneyByUid(uid,money);
        return  count;
    }


}
