package com.lingyu.login.web;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lingyu.login.model.*;
import com.lingyu.login.service.UserDetailsService;
import com.lingyu.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/Userdetails/getTarget")
    private @ResponseBody Object getTarget(@RequestBody Map<String,Object> param){
        Map<String,Object> map = new HashMap<>();
        String user= (String) param.get("target");
        String token= (String) param.get("token");
        DecodedJWT verify= JWTUtils.verify(token);
        String fUid=verify.getClaim("uid").asString();
        Visitors visitors=userDetailsService.getVisitorsByfUidAndtUid(fUid,user);

        if(visitors==null){
            int count=userDetailsService.insertNewVisitors(fUid,user);
            if(count==0){
                return false;
            }
        }else{
            int countDelete=userDetailsService.delete(fUid,user);
            if(countDelete==1){
                int countInsert=userDetailsService.insertNewVisitors(fUid,user);
                if(countInsert==0){
                    return false;
                }
            }
        }

        UserInfo intell=userDetailsService.getUserInfoByUid(user);
        List<UserImg> img=userDetailsService.getUserImgByUid(user);
        User base=userDetailsService.getUserByUid(user);
        List<UserHobby> hobby=userDetailsService.getHobbyByUid(user);
        List<UserTag> tag=userDetailsService.getTagByUid(user);
        UserPartner req=userDetailsService.getPartnerByUid(user);
        List<Gift> gift=userDetailsService.getGift();
        UserFocus isfocus=userDetailsService.getFocusByfUidAndUId(fUid,user);
        String foc=null;
        if(isfocus!=null){
            foc="3";
        }else {
            foc="1";
        }
        map.put("base",base);
        map.put("intell",intell);
        map.put("img",img);
        map.put("hobby",hobby);
        map.put("tag",tag);
        map.put("one",req);
        map.put("gift",gift);
        map.put("foc",foc);
        return map;
    }


    @PostMapping("/Userdetails/getMine")
    private @ResponseBody Object getMine(@RequestBody Map<String,Object> param, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String token= (String) param.get("token");
        DecodedJWT verify= JWTUtils.verify(token);
        if(verify!=null){
            User user= (User) request.getSession().getAttribute("account");
            return user;
        }
        return 1;
    }
}
