package com.lingyu.active.mapper;

import com.lingyu.active.model.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer actid);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer actid);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> getActivityList();


    int test(Activity ac);
}