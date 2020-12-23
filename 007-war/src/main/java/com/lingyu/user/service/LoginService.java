package com.lingyu.user.service;

import com.lingyu.user.model.Emp;

public interface LoginService {


    Emp getEmp(String nam, String pwd);
}
