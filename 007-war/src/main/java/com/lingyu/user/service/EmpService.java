package com.lingyu.user.service;

import com.lingyu.user.model.Emp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmpService {


    Integer getTotalByAccount(String account);

    List<Emp> getEmpByAccount(String account, Integer start, Integer per_page);

    List<Emp> getEmp();

    @Transactional
    int modifyEmp(String empid, String nickname, String roleid, String xpwd);

    Emp getEmpById(String empid);

    Emp getEmpForAccount(String account);

    @Transactional
    int insert(String account, String pwd, String nickname, String roleid);

    int deleteByEmpid(String empid);
}
