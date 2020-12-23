package com.lingyu.login.mapper;

import com.lingyu.login.model.UserFocus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFocusMapper {
    int deleteByPrimaryKey(Integer focusid);

    int insert(UserFocus record);

    int insertSelective(UserFocus record);

    UserFocus selectByPrimaryKey(Integer focusid);

    int updateByPrimaryKeySelective(UserFocus record);

    int updateByPrimaryKey(UserFocus record);

    UserFocus getFocusByfUidAndUId(String fUid, String user);
}