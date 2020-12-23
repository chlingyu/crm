package com.lingyu.login.mapper;

import com.lingyu.login.model.Gift;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GiftMapper {
    int deleteByPrimaryKey(Integer giftid);

    int insert(Gift record);

    int insertSelective(Gift record);

    Gift selectByPrimaryKey(Integer giftid);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);

    List<Gift> getGift();
}