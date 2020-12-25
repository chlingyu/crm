package com.lingyu.order.service.serviceImpl;

import com.lingyu.login.mapper.RecordActMapper;
import com.lingyu.login.model.RecordAct;
import com.lingyu.login.model.RecordActAndActivity;
import com.lingyu.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RecordActMapper recordActMapper;
    @Override
    public int modifyRecordStateByUidAndStateAndNowTime(Integer uid, String nowTime) {
        int count=recordActMapper.modifyRecordStateByUidAndStateAndNowTime(uid,nowTime);
        return count;
    }

    @Override
    public Integer countRecordActByUid(Integer uid) {
        Integer count= recordActMapper.countRecordActByUid(uid);
        return count;
    }

    @Override
    public Integer countRecordActByUidAndState(Integer uid) {
        int count=recordActMapper.countRecordActByUidAndState(uid);
        return count;
    }

    @Override
    public Integer countRecordActByUidAndOneState(Integer uid, String sql) {
        int count=recordActMapper.countRecordActByUidAndOneState(uid,sql);
        return count;
    }

    @Override
    public List<RecordActAndActivity> getAllOrder(Integer uid, Integer index,  Integer maxRow) {
        List<RecordActAndActivity>  list=recordActMapper.getAllOrder(uid,index,maxRow);
        return list;
    }

    @Override
    public List<RecordActAndActivity> getOrderByQt(Integer uid, Integer index,  Integer maxRow) {
        List<RecordActAndActivity>  list=recordActMapper.getOrderByQt(uid,index,maxRow);
        return list;
    }

    @Override
    public List<RecordActAndActivity> getOrderBySql(Integer uid, Integer index,  Integer maxRow, String sql) {
        List<RecordActAndActivity>  list=recordActMapper.getOrderBySql(uid,index,maxRow,sql);
        return list;
    }
}
