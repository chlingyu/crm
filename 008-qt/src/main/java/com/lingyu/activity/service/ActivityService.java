package com.lingyu.activity.service;

import com.lingyu.login.model.*;

import java.util.List;

public interface ActivityService {
    Integer countAct(String nowTime, String city, String keywords);

    List<Activity> getAct(String nowTime, String city, String keywords, Integer per, Integer maxRow);

    Integer countOldAct(String nowTime);

    List<Activity> getOldAct(String nowTime, Integer per, Integer maxRow);

    Activity getActByActid(String actid);

    RecordAct getRecordActByUidAndActId(String uid, String actid);

    List<Activity> getActBySimilarCity(String actid, String actCity);

    List<UserAndRecordAct> getUserByActIdAndState(String actid);

    Integer countComActByActId(String actid);

    List<UserAndComAct> getUserAndComActByActId(String actid);

    int addRecordAct(String orderId, String uid, String actid);

    int modifyRecordStateByUidAndActId(String uid, String actid);
}
