package com.lingyu.user.mapper;

import com.lingyu.user.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role selectRole(Integer roleid);

    List<Role> getRoleList();

    Role getRoleByName(String roleName);

    int insertRoleByName(String roleName);

    int updateNameById(String roleName, Integer roleid);
}