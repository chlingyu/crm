package com.lingyu.order.service;

import com.lingyu.login.model.RecordActAndActivity;

import java.util.List;

public interface OrderService {
    int modifyRecordStateByUidAndStateAndNowTime(Integer uid, String nowTime);

    Integer countRecordActByUid(Integer uid);

    Integer countRecordActByUidAndState(Integer uid);

    Integer countRecordActByUidAndOneState(Integer uid, String sql);

    List<RecordActAndActivity> getAllOrder(Integer uid, Integer index,  Integer maxRow);

    List<RecordActAndActivity> getOrderByQt(Integer uid, Integer index,  Integer maxRow);

    List<RecordActAndActivity> getOrderBySql(Integer uid, Integer index,  Integer maxRow, String sql);
}
