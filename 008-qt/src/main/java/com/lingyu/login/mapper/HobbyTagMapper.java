package com.lingyu.login.mapper;

import com.lingyu.login.model.HobbyTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HobbyTagMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(HobbyTag record);

    int insertSelective(HobbyTag record);

    HobbyTag selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(HobbyTag record);

    int updateByPrimaryKeyWithBLOBs(HobbyTag record);

    List<HobbyTag> selectAll();
}