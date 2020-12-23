package com.lingyu.login.mapper;

import com.lingyu.login.model.Activity;
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

    List<Activity> getActivityByActCity(String city);

    Integer countAct(String nowTime, String city, String keywords);

    List<Activity> getAct(String nowTime, String city, String keywords, Integer per, Integer maxRow);

    int countOldAct(String nowTime);

    List<Activity> getOldAct(String nowTime, Integer per, Integer maxRow);

    Activity getActByActid(String actid);

    List<Activity> getActBySimilarCity(String actid, String actCity);
}