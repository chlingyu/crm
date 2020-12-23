package com.lingyu.login.mapper;

import com.lingyu.login.model.Visitors;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitorsMapper {
    int deleteByPrimaryKey(Integer vid);

    int insert(Visitors record);

    int insertSelective(Visitors record);

    Visitors selectByPrimaryKey(Integer vid);

    int updateByPrimaryKeySelective(Visitors record);

    int updateByPrimaryKey(Visitors record);

    Visitors getVisitorsByfUidAndtUid(String fUid, String user);

    int insertNewVisitors(String fUid, String user);

    int deleteByfUidAndtUid(String fUid, String user);
}