package com.lingyu.web;

import com.lingyu.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        User user=new User();
        user.setId("1");
        user.setName("haha");
        request.getSession().setAttribute("user",user);
        return "登陆成功";
    }

    @RequestMapping("/error")
    public String loginerror(){
        return  "登陆失败";
    }

    @RequestMapping("detail")
    public String detail(){
        return "访问内部成功";
    }

}
