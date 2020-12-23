package com.lingyu.user.service.impl;

import com.lingyu.user.mapper.EmpMapper;
import com.lingyu.user.model.Emp;
import com.lingyu.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public Emp getEmp(String nam, String pwd) {
        Emp emp=empMapper.selectEmp(nam,pwd);
        return emp;
    }
}
