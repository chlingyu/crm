package com.lingyu.user.mapper;

import com.lingyu.user.model.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EmpMapper {
    int deleteByPrimaryKey(Integer empid);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer empid);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    Emp selectEmp(String nam, String pwd);


    int getTotalByAccount(String account);

    List<Emp> getEmpByAccount(String account, Integer start, Integer per_page);

    List<Emp> getEmpList();

    int updateEmp(String empid, String nickname, String roleid, String xpwd);

    Emp getEmpById(String empid);

    Emp getEmpForAccount(String account);

    int insertEmp(String account, String pwd, String nickname, String roleid);

    int deleteByEmpid(String empid);
}