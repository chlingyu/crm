package com.lingyu.login.mapper;

import com.lingyu.login.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordActMapper {
    int deleteByPrimaryKey(String ractid);

    int insert(RecordAct record);

    int insertSelective(RecordAct record);

    RecordAct selectByPrimaryKey(String ractid);

    int updateByPrimaryKeySelective(RecordAct record);

    int updateByPrimaryKey(RecordAct record);

    RecordAct getRecordActByUidAndActId(String uid, String actid);

    List<UserAndRecordAct> getUserByActIdAndState(String actid);

    int insertRecordAct(String orderId, String uid, String actid);

    int modifyRecordStateByUidAndActId(String uid, String actid);

    Activity getActByOrderId(String orderid);

    int modifyRecordStateByOrderId(String orderid);

    int modifyRecordStateByUidAndStateAndNowTime(Integer uid, String nowTime);

    Integer countRecordActByUid(Integer uid);

    int countRecordActByUidAndState(Integer uid);

    int countRecordActByUidAndOneState(Integer uid, String sql);

    List<RecordActAndActivity> getAllOrder(Integer uid, Integer index,  Integer maxRow);

    List<RecordActAndActivity> getOrderByQt(Integer uid, Integer index,  Integer maxRow);

    List<RecordActAndActivity> getOrderBySql(Integer uid, Integer index,  Integer maxRow, String sql);
}