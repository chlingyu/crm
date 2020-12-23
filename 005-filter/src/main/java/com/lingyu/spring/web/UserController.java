package com.lingyu.spring.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/login")
    public @ResponseBody String login(){
        return "登录成功";
    }

    @RequestMapping("/login1")
    public @ResponseBody String login2(){
        return "登录成功1";
    }

}
