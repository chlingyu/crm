package com.lingyu.user.service;

import com.lingyu.user.model.User;

import java.util.List;

public interface UserService {
    List<User> getUser(Integer start, Integer per_page);

    List<User> getUserBy(String p_keyword, Integer start, Integer per_page);

    Integer getTotal(String p_keyword);

    Integer count();

    boolean modifyStatusLock(Integer uid);

    boolean modifyStatusOpen(Integer uid);

    int modifyPwd(Integer uid);
}
