package com.lingyu.user.service.impl;

import com.lingyu.user.mapper.EmpMapper;
import com.lingyu.user.model.Emp;
import com.lingyu.user.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public Integer getTotalByAccount(String account) {
        int count=empMapper.getTotalByAccount(account);
        return count;
    }

    @Override
    public List<Emp> getEmpByAccount(String account, Integer start, Integer per_page) {
        List<Emp> empList=empMapper.getEmpByAccount(account,start,per_page);
        return empList;
    }

    @Override
    public List<Emp> getEmp() {
        List<Emp> empList=empMapper.getEmpList();
        return empList;
    }

    @Override
    public int modifyEmp(String empid, String nickname, String roleid, String xpwd) {
        int count=empMapper.updateEmp(empid,nickname,roleid,xpwd);
        return count;
    }

    @Override
    public Emp getEmpById(String empid) {
        Emp emp=empMapper.getEmpById(empid);
        return emp;
    }

    @Override
    public Emp getEmpForAccount(String account) {
        Emp emp=empMapper.getEmpForAccount(account);
        return emp;
    }

    @Override
    public int insert(String account, String pwd, String nickname, String roleid) {
        int count = empMapper.insertEmp(account,pwd,nickname,roleid);
        return count;
    }

    @Override
    public int deleteByEmpid(String empid) {
        int count=empMapper.deleteByEmpid(empid);
        return count;
    }

}
