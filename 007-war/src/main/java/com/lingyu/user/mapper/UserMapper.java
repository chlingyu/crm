package com.lingyu.user.mapper;

import com.lingyu.user.model.User;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("addtime") Date addtime);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("uid") Integer uid, @Param("addtime") Date addtime);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    List<User> getUser(@Param("start") Integer start, @Param("per_page")Integer per_page);

    List<User> getUserBy(@Param("p_keyword")String p_keyword, @Param("start")Integer start, @Param("per_page")Integer per_page);

    Integer getTotal(@Param("p_keyword")String p_keyword);

    Integer getAccount();

    int modifyStatusLock(Integer uid);

    int modifyStatusOpen(Integer uid);

    int modifyPwd(Integer uid);
}