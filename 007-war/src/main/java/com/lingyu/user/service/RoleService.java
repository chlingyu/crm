package com.lingyu.user.service;

import com.lingyu.user.model.Role;

import java.util.List;

public interface RoleService {

    Role getRole(Integer roleid);

    List<Role> getDataList();

    int del(Integer roleid);

    Role getRoleByName(String roleName);

    int insertRole(String roleName);

    int modNameById(String roleName, Integer roleid);
}
