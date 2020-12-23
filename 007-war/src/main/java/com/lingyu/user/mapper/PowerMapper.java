package com.lingyu.user.mapper;

import com.lingyu.user.model.Power;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PowerMapper {
    int deleteByPrimaryKey(Integer powerid);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Integer powerid);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);

    List getPowerByRoleId(Integer roleid);

    int insertRoleidAndMenuid(@Param("roleid") Integer roleid, @Param("a") Integer[] a);

    int deleteByRoleId(Integer roleid);
}