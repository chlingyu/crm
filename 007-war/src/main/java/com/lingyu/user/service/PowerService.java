package com.lingyu.user.service;

import java.util.List;
import java.util.Map;

public interface PowerService {
    List getPowerByRoleId(Integer roleid);


    int insertRoleidAndMenuid(Integer roleid, Integer[] a);

    int deleteByRoleId(Integer roleid);
}
