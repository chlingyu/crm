package com.lingyu.user.service.impl;

import com.lingyu.user.mapper.PowerMapper;
import com.lingyu.user.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper powerMapper;
    @Override
    public List getPowerByRoleId(Integer roleid) {
        List result=powerMapper.getPowerByRoleId(roleid);
        return result;
    }

    @Override
    public int insertRoleidAndMenuid(Integer roleid, Integer[] a) {
        int count=powerMapper.insertRoleidAndMenuid(roleid,a);
        return count;
    }

    @Override
    public int deleteByRoleId(Integer roleid) {
        int count=powerMapper.deleteByRoleId(roleid);
        return count;
    }


}
