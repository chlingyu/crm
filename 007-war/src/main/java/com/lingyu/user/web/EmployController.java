package com.lingyu.user.web;


import com.lingyu.user.mapper.EmpMapper;
import com.lingyu.user.mapper.MenuMapper;
import com.lingyu.user.model.Emp;
import com.lingyu.user.model.Menu;
import com.lingyu.user.model.Role;
import com.lingyu.user.service.EmpService;
import com.lingyu.user.service.MenuService;
import com.lingyu.user.service.PowerService;
import com.lingyu.user.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private PowerService powerService;
    @Autowired
    private EmpService empService;

    @GetMapping("/power/getData")
    private  @ResponseBody List<Role> getEmpData(HttpServletRequest request)  {
        List<Role> roleList=roleService.getDataList();
        return roleList;
    }


    @PostMapping("/power/del")
    private @ResponseBody Map del(@RequestBody Map<String,Object> param)  {
        Map<String,Object> map = new HashMap<>();
        Integer roleid= (Integer) param.get("id");
        if(roleid==null){
            map.put("errcode",10001);
            map.put("errmsg","无法正确获取删除的角色id");
            return map;
        }
        int count=roleService.del(roleid);
        int deletecount=powerService.deleteByRoleId(roleid);
        if(count!=0){
            map.put("success",true);
           return map;
        }
        map.put("errcode",10002);
        map.put("errmsg","该角色已被使用，无法删除！");
        return map;
    }


    @PostMapping("/power/add")
    private @ResponseBody Map add(@RequestBody Map<String,Object> param)  {
        Map<String,Object> map = new HashMap<>();
        String roleName= (String) param.get("name");
        if(roleName==null){
            map.put("errcode",10001);
            map.put("errmsg","无法正确获取角色名称");
            return map;
        }
        Role role=roleService.getRoleByName(roleName);
        if(role!=null){
            map.put("errcode",10002);
            map.put("errmsg","该角色名称已被使用，请重新输入");
            return map;
        }
        int count=roleService.insertRole(roleName);
        if(count!=0){
            map.put("success",true);
        }
        return map;
    }



    @PostMapping("/power/mod")
    private @ResponseBody Map mod(@RequestBody Map<String,Object> param) {
        Map<String,Object> map = new HashMap<>();
        String roleName= (String) param.get("name");
        Integer roleid= (Integer) param.get("id");
        if(roleName==null){
            map.put("errcode",10001);
            map.put("errmsg","无法正确获取角色名称");
            return map;
        }
        if(roleid==0){
            map.put("errcode",10002);
            map.put("errmsg","无法正确获取修改的角色id");
            return map;
        }
        Role role=roleService.getRoleByName(roleName);
        if(role!=null){
            map.put("errcode",10003);
            map.put("errmsg","该角色名称已被使用，请重新输入");
            return map;
        }
        int count=roleService.modNameById(roleName,roleid);
        if(count!=0){
            map.put("success",true);
        }
        return map;
    }

    @GetMapping("/power/getPower")
    private @ResponseBody Map getPower(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        String s_roleid= request.getParameter("id");
        List<Menu> menuList=menuService.getMenu();
        if(s_roleid==null){
            map.put("errcode",10001);
            map.put("errmsg","无法正确获取修改的角色id");
            return map;
        }
        Integer roleid=Integer.valueOf(s_roleid);
        List result=powerService.getPowerByRoleId(roleid);
        map.put("all",menuList);
        map.put("pri",result);
        return map;
    }


    @PostMapping("/power/changePower")
    private @ResponseBody Map changePower(@RequestParam Map<String,Object> param ) {

        Map<String,Object> map = new HashMap<>();
        Map<String,Object> arrmap = new HashMap<>();
        String s_roleid= (String) param.get("rid");

        Integer roleid=Integer.valueOf(s_roleid);
        int countdelete=powerService.deleteByRoleId(roleid);

        //接收数字字符串“{1,2,3}”
        String str= (String) param.get("arr");
        //把“{1,2,3}”转换为 {“1”，“2”，“3”}
        String[] arr= str.split(",");

        //最后把字符串数组变为整数类型数组 {1,2,3}
        Integer[] a=new Integer[arr.length];

        for(int i=0;i<arr.length;i++){
            a[i]=Integer.valueOf(arr[i]);
        }

        int count=powerService.insertRoleidAndMenuid(roleid,a);

        if(count!=0){
            map.put("success",true);
        }
        return map;
    }

    @PostMapping("/Emp/Check")
    private @ResponseBody Object Check(@RequestBody Map<String,Object> param) {
        Map<String,Object> map = new HashMap<>();
        String s_account= (String) param.get("account");
        String account=(s_account==null || s_account.trim().equals(""))?"":s_account.trim();
        Object s_page= (Object) param.get("page");
        Integer page=0;
        if(s_page instanceof Integer){
           page=Integer.parseInt(s_page.toString());
        }else{
            page=1;
        }

        if(page<1){
            page=1;
        }
        String s_perpage= (String) param.get("perPage");
        Integer per_page=(s_perpage==null)?5:Integer.valueOf(s_perpage);
        Integer total= empService.getTotalByAccount(account);
        Integer totalPage=(int)Math.ceil(total/per_page)+1;
        if(total%per_page==0){
            totalPage=(int)Math.ceil(total/per_page);  //可以整除 表示页面数量刚好
        }
      /*  if(page>total){
            map.put("state",100);
        }*/
        Integer start=(page-1)*per_page;
        List<Emp> empList= empService.getEmpByAccount(account,start,per_page);
        Integer nowPage=0;
        if(s_page instanceof Integer){
            nowPage=Integer.parseInt(s_page.toString());
        }else{
            nowPage=1;
        }

        if(nowPage>totalPage || nowPage<=0){
            return map;
        }
        List<Role> roleList=roleService.getDataList();
        map.put("state",1);
        map.put("user",empList);
        map.put("role",roleList);
        map.put("total_num",total);
        map.put("nowPage",nowPage);
        map.put("totalPage",totalPage);
        return map;
    }

    @GetMapping("/Emp/Count")
    private @ResponseBody Map Count(@RequestBody Map<String,Object> param) {
        Map<String,Object> map = new HashMap<>();
        List<Emp> empList=empService.getEmp();
        if(empList!=null){
            map.put("success",1);
            map.put("res",empList.size());
            return map;
        }
        map.put("errocode",1);
        map.put("errmsg","错误");
        return map;
     }

     //修改员工信息
    @GetMapping("/Emp/Change")
    private @ResponseBody Map Change(HttpServletRequest request) {
        String account=request.getParameter("account");
        String pwd=request.getParameter("pwd");
        String empid=request.getParameter("empid");
        String nickname=request.getParameter("nickname");
        String roleid=request.getParameter("roleid");

        Map<String,Object> map = new HashMap<>();
        if(nickname==""){
            map.put("errocode",10000);
            map.put("errmsg","名称不能为空");
            return map;
        }if(roleid==""){
            map.put("errocode",10000);
            map.put("errmsg","职位不能为空");
            return map;
        }
        String regexAccount="^[a-zA-Z][a-zA-Z0-9_]{4,15}$";   //字母开头，允许5-16字节，允许字母数字下划线
        if(!account.matches(regexAccount)){
            map.put("errocode",10000);
            map.put("errmsg","用户名由6-16位字符，字母开头，只包含字母数字下划线");
            return map;
        }
        String regexPwd="^[a-zA-Z]\\w{5,17}$";
        if(!pwd.matches(regexPwd)){
            map.put("errocode",10000);
            map.put("errmsg","密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线");
            return map;
        }

        Emp emp=empService.getEmpById(empid);
        if(empid!="") {  //进入到修改模块
            String xpwd = request.getParameter("xpwd");
            if (!xpwd.matches(regexPwd)) {  //新密码校验
                map.put("errocode", 10000);
                map.put("errmsg", "密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线！");
                return map;
            }

            String pwd2=emp.getPwd();
            if(!emp.getPwd().equals(pwd)){
                map.put("errocode", 10000);
                map.put("errmsg", "旧密码错误！");
                return map;
            }
            int count = empService.modifyEmp(empid, nickname, roleid, xpwd); //更新新的信息(包括员工名称，角色id,新密码)
            if (count != 0) {
                map.put("success", true);
                map.put("errmsg", "修改成功");
            }
        }else{
            Emp empForAccount=empService.getEmpForAccount(account);
            if(empForAccount!=null){
                map.put("errocode", 10000);
                map.put("errmsg", "用户已存在！");
                return map;
            }
            int count=empService.insert(account,pwd,nickname,roleid);
            if(count!=0){
                map.put("success", true);
                map.put("errmsg", "添加成功");
            }

        }

        return map;
    }


    @GetMapping("/Emp/Chang")
    private @ResponseBody Map delete(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        String empid=request.getParameter("empid");
        int count=empService.deleteByEmpid(empid);
        if(count!=0){
            map.put("success", true);
            map.put("errmsg", "删除成功");
            return map;
        }
        map.put("errocode", 10000);
        map.put("errmsg", "删除失败！");
        return map;
    }

}


