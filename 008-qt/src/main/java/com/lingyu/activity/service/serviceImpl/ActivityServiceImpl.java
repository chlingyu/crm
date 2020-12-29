package com.lingyu.activity.service.serviceImpl;

import com.lingyu.activity.service.ActivityService;
import com.lingyu.login.mapper.ActivityMapper;
import com.lingyu.login.mapper.ComActMapper;
import com.lingyu.login.mapper.RecordActMapper;
import com.lingyu.login.mapper.UserMapper;
import com.lingyu.login.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private RecordActMapper recordActMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ComActMapper comActMapper;
    @Override
    public Integer countAct(String nowTime, String city, String keywords) {
        Integer total=activityMapper.countAct(nowTime,city,keywords);
        return total;
    }

    @Override
    public List<Activity> getAct(String nowTime, String city, String keywords, Integer per, Integer maxRow) {
        List<Activity> activityList=activityMapper.getAct(nowTime,city,keywords,per,maxRow);
        return activityList;
    }

    @Override
    public Integer countOldAct(String nowTime) {
        int count=activityMapper.countOldAct(nowTime);
        return  count;
    }

    @Override
    public List<Activity> getOldAct(String nowTime, Integer per, Integer maxRow) {
        List<Activity> activityList=activityMapper.getOldAct(nowTime,per,maxRow);
        return activityList;
    }

    @Override
    public Activity getActByActid(String actid) {
        Activity activity=activityMapper.getActByActid(actid);
        return activity;
    }




    @Override
    public RecordAct getRecordActByUidAndActId(String uid, String actid) {
        RecordAct recordAct=recordActMapper.getRecordActByUidAndActId(uid,actid);
        return recordAct;
    }

    @Override
    public List<Activity> getActBySimilarCity(String actid, String actCity) {
        List<Activity> activity=activityMapper.getActBySimilarCity(actid,actCity);
        return activity;
    }

    @Override
    public List<UserAndRecordAct> getUserByActIdAndState(String actid) {
        List<UserAndRecordAct> user=recordActMapper.getUserByActIdAndState(actid);
        return user;
    }

    @Override
    public Integer countComActByActId(String actid) {
        Integer count=comActMapper.countComActByActId(actid);
        return count;
    }

    @Override
    public List<UserAndComAct> getUserAndComActByActId(String actid) {
        List<UserAndComAct> userAndComActs=comActMapper.getUserAndComActByActId(actid);
        return userAndComActs;
    }

    @Override
    public int addRecordAct(String orderId, String uid, String actid) {
        int count=recordActMapper.insertRecordAct(orderId,uid,actid);
        return count;
    }

    @Override
    public int modifyRecordStateByUidAndActId(String uid, String actid) {
        int count=recordActMapper.modifyRecordStateByUidAndActId(uid,actid);
        return count;
    }
    @Override
    public Activity getActByOrderId(String orderid) {
        Activity activity=recordActMapper.getActByOrderId(orderid);
        return  activity;
    }

    @Override
    public int modifyUserMoneyByUid(String uid,float yu) {
        int count=userMapper.modifyUserMoneyByUid(uid,yu);
        return count;
    }

    @Override
    public int modifyRecordStateByOrderId(String orderid) {
        int count=recordActMapper.modifyRecordStateByOrderId(orderid);
        return  count;
    }

    @Override
    public float getMoneyByUid(String uid) {
        float money=userMapper.getMoneyByUid(uid);
        return money;
    }
}
