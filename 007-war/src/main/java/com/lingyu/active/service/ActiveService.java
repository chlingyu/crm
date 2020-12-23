package com.lingyu.active.service;

import com.lingyu.active.model.Activity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActiveService {
    List<Activity> getActivityList();

    @Transactional
    int test(Activity ac);
}
