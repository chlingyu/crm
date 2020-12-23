package com.lingyu.login.mapper;

import com.lingyu.login.model.UserPartner;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPartnerMapper {
    int deleteByPrimaryKey(Integer partnerid);

    int insert(UserPartner record);

    int insertSelective(UserPartner record);

    UserPartner selectByPrimaryKey(Integer partnerid);

    int updateByPrimaryKeySelective(UserPartner record);

    int updateByPrimaryKey(UserPartner record);

    UserPartner getPartnerByUid(String user);
}