package com.lingyu.active.service.impl;

import com.lingyu.active.mapper.ActivityMapper;
import com.lingyu.active.model.Activity;
import com.lingyu.active.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> getActivityList() {
        List<Activity>  activityList= activityMapper.getActivityList();
        return activityList;
    }

    @Override
    public int test(Activity ac) {
        int count = activityMapper.test(ac);
        return count;
    }


}
