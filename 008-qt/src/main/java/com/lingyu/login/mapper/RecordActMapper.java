package com.lingyu.login.mapper;

import com.lingyu.login.model.RecordAct;
import com.lingyu.login.model.User;
import com.lingyu.login.model.UserAndRecordAct;
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
}