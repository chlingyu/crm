package com.lingyu.user.web;


import com.lingyu.user.model.Log;
import com.lingyu.user.model.Menu;
import com.lingyu.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/Admin")
public class AdminController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/getMenu")
    public @ResponseBody Map getMenu(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Log log= (Log) request.getSession().getAttribute("log");
        Integer roleid=log.getRoleid();
        List<Menu> menuList = menuService.getMenuByRoleid(roleid);
        /*List<Menu> menuList = menuService.getMenu();*/
        if (menuList == null) {
            map.put("error", 101);
            map.put("resp", "获取错误");
            return map;
        }
        map.put("success", 1);
        map.put("resp", menuList);
        return map;
    }

    @RequestMapping("/getUserInfo")
    public @ResponseBody Map getUserInfo(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        Log log= (Log) request.getSession().getAttribute("log");
        if(log==null){
            map.put("error", 101);
            map.put("resp", "请先登录");
        }
        map.put("success",1);
        map.put("account",log.getAccount());
        map.put("roleName",log.getRoleName());
        return map;
    }
}
