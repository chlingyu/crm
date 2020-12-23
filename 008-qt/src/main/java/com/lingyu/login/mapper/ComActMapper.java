package com.lingyu.login.mapper;

import com.lingyu.login.model.ComAct;
import com.lingyu.login.model.UserAndComAct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ComActMapper {
    int deleteByPrimaryKey(Integer comactid);

    int insert(ComAct record);

    int insertSelective(ComAct record);

    ComAct selectByPrimaryKey(Integer comactid);

    int updateByPrimaryKeySelective(ComAct record);

    int updateByPrimaryKeyWithBLOBs(ComAct record);

    int updateByPrimaryKey(ComAct record);

    Integer countComActByActId(String actid);

    List<UserAndComAct> getUserAndComActByActId(String actid);
}