package com.lingyu.login.web;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lingyu.login.model.*;
import com.lingyu.login.service.AdminService;
import com.lingyu.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/Index/getUserInfo")
    private @ResponseBody Object getUserInfo(HttpServletRequest request){
        String token=request.getParameter("token");
        DecodedJWT verify=JWTUtils.verify(token);
        String uid=verify.getClaim("uid").asString();
        UserAndInfo userAndInfo =adminService.getUserJoinInfo(uid);
        return userAndInfo;
    }

    @GetMapping("/Index/getActivit")
    private @ResponseBody Object getActivity(HttpServletRequest request){
        String city=request.getParameter("city");
        List<Activity> activityList=adminService.getActivityByActCity(city);
        return activityList;
    }

    @GetMapping("/Index/getRecommend")
    private @ResponseBody Object getRecommend(HttpServletRequest request){
        List<UserAndInfoAndPartner> userAndInfoAndPartnerList= adminService.getRecommend();
        return userAndInfoAndPartnerList;
    }
}
