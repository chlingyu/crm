package com.lingyu.user.web;

import com.lingyu.user.model.Emp;
import com.lingyu.user.model.Log;
import com.lingyu.user.model.Role;
import com.lingyu.user.service.LoginService;
import com.lingyu.user.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/Login")
public class LoginController  {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @PostMapping(value = "/doLogin")
    public @ResponseBody Map login(@RequestBody Map<String,String> param, HttpServletRequest request){
        String nam=param.get("nam");
        String pwd=param.get("pwd");
        Map<String,Object> map = new HashMap<>();
        Emp employ = loginService.getEmp(nam,pwd);
        if(employ != null){
            Integer roleid=employ.getRoleid();
            Role role=roleService.getRole(roleid);
            map.put("success",true);
            map.put("emp",employ);
            map.put("role",role);
            Log log=new Log();
            log.setAccount(employ.getAccount());
            log.setEmpid(employ.getEmpid());
            log.setRoleid(employ.getRoleid());
            log.setNickname(employ.getNickname());
            log.setRoleName(role.getRoleName());
            request.getSession().setAttribute("log",log);
            return map;
        }
        map.put("errocode",10000);
        map.put("errmsg","账号或密码错误");
        return map;
    }

}
