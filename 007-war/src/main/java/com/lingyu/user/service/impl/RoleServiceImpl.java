package com.lingyu.user.service.impl;

import com.lingyu.user.mapper.RoleMapper;
import com.lingyu.user.model.Role;
import com.lingyu.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRole(Integer roleid) {
        Role role=roleMapper.selectRole(roleid);
        return role;
    }

    @Override
    public List<Role> getDataList() {
        List<Role> roleList=roleMapper.getRoleList();
        return roleList;
    }

    @Override
    @Transactional
    public int del(Integer roleid) {
        int count=roleMapper.deleteByPrimaryKey(roleid);
        return count;
    }

    @Override
    public Role getRoleByName(String roleName) {
        Role role=roleMapper.getRoleByName(roleName);
        return role;
    }

    @Override
    public int insertRole(String roleName) {
        int count=roleMapper.insertRoleByName(roleName);
        return count;
    }

    @Override
    public int modNameById(String roleName, Integer roleid) {
        int count=roleMapper.updateNameById(roleName,roleid);
        return count;
    }
}
