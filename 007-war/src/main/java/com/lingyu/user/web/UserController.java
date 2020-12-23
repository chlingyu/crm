package com.lingyu.user.web;


import com.lingyu.user.model.User;
import com.lingyu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Userlist")
public class UserController extends HttpServlet {

    @Autowired
  private UserService userService;

    @PostMapping(value = "/getuserlist")
    public @ResponseBody Map getuserlist(@RequestBody Map<String,Object> param, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        Integer p_page =(Integer) param.get("page");
        Integer p_per_page =(Integer) param.get("perPage");
        Integer p_limiter =(Integer) param.get("limiter");
        String p_keyword =(String) param.get("key");
        if(p_keyword!=null){
            request.getSession().setAttribute("p_keyword",p_keyword);
        }
        String receive = (String) request.getSession().getAttribute("p_keyword");
        String test=receive.trim();
        if(test!=null){
            p_keyword=test;
        }



        Integer page = (p_page == null) ? 1: p_page;
        Integer per_page = (p_per_page ==  null) ? 5 : p_per_page;
        Integer start = (page-1)*per_page;
        List<User> userList1 = new ArrayList<>();

        if(p_limiter != null){
            if(p_limiter<page || page<=0){
                return map;
            }
        }

        if(p_keyword == null){
            //从下标为start的数据开始查
            userList1 = userService.getUser(start,per_page);
            Integer total = userService.count();
            Integer totalPage = (int) Math.ceil(total/per_page)+1;
            Integer nowPage = (page==null) ? 1:page;
            map.put("state",1);
            map.put("user",userList1);
            map.put("total_num",total);
            map.put("nowPage",nowPage);
            map.put("totalPage",totalPage);
            map.put("keyword","");
            return map;

        }else{
            userList1 = userService.getUserBy(p_keyword,start,per_page);
            Integer total = userService.getTotal(p_keyword);
            Integer nowPage = (page==null) ? 1:page;
            Integer totalPage=0;
            if(total%per_page!=0){
                totalPage = (int) Math.ceil(total/per_page)+1;
            }else {totalPage = (int) Math.ceil(total/per_page);}

            map.put("state",1);
            map.put("user",userList1);
            map.put("total_num",total);
            map.put("nowPage",nowPage);
            map.put("totalPage",totalPage);
            map.put("keyword",p_keyword);
            return map;
        }
    }



    @PostMapping(value = "/rewriteState")
    public  @ResponseBody String getwriteState(@RequestBody Map<String,Object> param){
        String status = (String) param.get("status");
        Integer uid = (Integer) param.get("uid");
        if("1".equals(status)){
            boolean flag=userService.modifyStatusLock(uid);
            System.out.println(flag);
            if(flag){ return "锁定";}
        }
           boolean  flag=userService.modifyStatusOpen(uid);
            System.out.println(flag);
            return "使用";
    }

    @PostMapping(value = "/ResetPwd")
    public  @ResponseBody Object ResetPwd(@RequestBody Map<String,Object> param){
        Integer uid= (Integer) param.get("uid");
        int count=userService.modifyPwd(uid);
        return count;
    }
}
