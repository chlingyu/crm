package com.lingyu.personal.web;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.lingyu.login.model.*;
import com.lingyu.personal.service.PersonalService;
import com.lingyu.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PersonalController {
    @Autowired
    private PersonalService personalService;
    @Value("${file.upload.url}")
    private String uploadFilePath;

    @PostMapping("/Personal/user")
    private @ResponseBody Map getUser(@RequestBody Map<String,Object> param, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        String token= (String) param.get("token");
        DecodedJWT verify= JWTUtils.verify(token);
        String uid=verify.getClaim("uid").asString();
        User user= (User) request.getSession().getAttribute("account");
        if(verify!=null){
            UserInfo res = personalService.getUserInfoByUid(uid);
            map.put("success",true);
            map.put("user",user);
            map.put("ge",res);
            return map;
        }
        map.put("errocode",1);
        return map;
    }

    @GetMapping("/Personal/guname")
    private @ResponseBody Map modifyUname(User user){
        Map<String,Object> map = new HashMap<>();
        String uid=user.getUid()==null?"": String.valueOf(user.getUid());
        String uname = user.getUname()==null ? "":user.getUname();
        if(uname!=null && uid!=null ){
            int count=personalService.modifyUnameByUid(uid,uname);
            if(count!=0){
                map.put("success",uid);
            }
        }
        return map;
    }
    @GetMapping("/Personal/ggr")
    private @ResponseBody Map modifyUser(HttpServletRequest request) throws ParseException {
        Map<String,Object> map = new HashMap<>();

        long birthday_l= Long.parseLong(request.getParameter("birthday"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = format.format(birthday_l);//用SimpleDateFormat将Date转xxxx-xx-xx格式的字符串日期
        Date birthday2=format.parse(birthday);
        UserInfo userInfo=new UserInfo();
        userInfo.setBirthday(birthday2);

        String uid=request.getParameter("uid");
        String income=request.getParameter("income");
        String height=request.getParameter("height");
        String education=request.getParameter("education");
        String weight=request.getParameter("weight");
        String marry=request.getParameter("marry");
        String workArea=request.getParameter("workArea");
        String address=request.getParameter("address");
        String has=request.getParameter("has");
        String want=request.getParameter("want");
        String Smoke=request.getParameter("Smoke");
        String Drink=request.getParameter("Drink");
        String age=request.getParameter("age");

        switch (income){
            case "1":income="1000~3000";break;
            case "2":income="3000~7000";break;
            case "3":income="7000~10000";break;
            case "4":income="10000~20000";break;
            case "5":income="20000~50000";break;
            case "6":income="50000以上";break;
        }
        switch (height){
            case "1":height="140~150";break;
            case "2":height="150~160";break;
            case "3":height="160~170";break;
            case "4":height="170~180";break;
            case "5":height="180~190";break;
            case "6":height="190以上";break;
        }
        switch (education){
            case "1":education="初中";break;
            case "2":education="高中";break;
            case "3":education="中专";break;
            case "4":education="大专";break;
            case "5":education="本科";break;
            case "6":education="硕士";break;
            case "7":education="博士";break;
        }
        switch (weight){
            case "1":weight="40~50";break;
            case "2":weight="50~60";break;
            case "3":weight="60~70";break;
            case "4":weight="70~80";break;
            case "5":weight="80~90";break;
            case "6":weight="90~100";break;
            case "7":weight="100以上";break;
        }
        switch (marry){
            case "1":marry="已婚";break;
            case "2":marry="未婚";break;
            case "3":marry="丧偶";break;
        }
        switch (workArea){
            case "1":workArea="国家机关";break;
            case "2":workArea="专业技术";break;
            case "3":workArea="办事人员";break;
            case "4":workArea="商业";break;
            case "5":workArea="生产人员";break;
            case "6":workArea="操作人员";break;
            case "7":workArea="军人";break;
            case "8":workArea="其他";break;
        }
        switch (has){
            case "1":has="有";break;
            case "2":has="无";break;

        }
        switch (want){
            case "1":want="都行";break;
            case "2":want="想";break;
            case "3":want="不想";break;

        }
        switch (Smoke){
            case "1":Smoke="是";break;
            case "2":Smoke="否";break;

        }
        switch (Drink){
            case "1":Drink="是";break;
            case "2":Drink="否";break;

        }


        userInfo.setUid(Integer.valueOf(uid));
        userInfo.setIncome(income);
        userInfo.setHeight(height);
        userInfo.setEducation(education);
        userInfo.setWeight(weight);
        userInfo.setMarry(marry);
        userInfo.setWorkArea(workArea);
        userInfo.setAddress(address);
        userInfo.setHasChild(has);
        userInfo.setWantChild(want);
        userInfo.setIsSmoke(Smoke);
        userInfo.setIsDrink(Drink);
        userInfo.setAge(Byte.valueOf(age));

        UserInfo existUserInfo=personalService.getUserInfoByUid(uid);
        if(existUserInfo!=null) {
            int count = personalService.modifyUserInfo(userInfo);
            if(count==1){
                map.put("success",true);
                return map;
            }
        }else {
            int count=personalService.addUserInfo(userInfo);
            if(count==1){
                map.put("success",true);
                return map;
            }
        }
        return map;
    }

    @GetMapping("/Personal/biao")
    private @ResponseBody Object getBiaoqian(@RequestParam Map param){
        Map<String,Object> map=new HashMap<>();
        String uid= (String) param.get("uid")==null?"":(String) param.get("uid");
        List<UserTag> userTags=personalService.getUserTagByUid(uid);
        List<Object> res=new ArrayList<>();
        for (UserTag data:userTags){
            Integer tid=data.getTid();
            HobbyTag hobbyTag=personalService.getHobbyTagByTid(tid);
            res.add(hobbyTag);
        }

        List<HobbyTag> u=personalService.getHobbyTag();
        res.add(u);
        map.put("success",true);
        map.put("hobby",res);
        return map;
    }
    @PostMapping("/Personal/qian")
    private @ResponseBody Object saveBiaoqian(@RequestBody Map param){
        Map<String,Object> map=new HashMap<>();
        Integer uid= (Integer) param.get("uid");
        Integer tid1= param.get("tid1")==null?0:(Integer) param.get("tid1");
        Integer tid2= param.get("tid2")==null?0:(Integer) param.get("tid2");
        Integer tid3= param.get("tid3")==null?0:(Integer) param.get("tid3");
        Integer tid4= param.get("tid4")==null?0:(Integer) param.get("tid4");
        Integer tid5= param.get("tid5")==null?0:(Integer) param.get("tid5");
        Integer tid6= param.get("tid6")==null?0:(Integer) param.get("tid6");
        Integer tid7= param.get("tid7")==null?0:(Integer) param.get("tid7");
        Integer tid8= param.get("tid8")==null?0:(Integer) param.get("tid8");
        Integer tid9= param.get("tid9")==null?0:(Integer) param.get("tid9");
        Integer tid0= param.get("tid0")==null?0:(Integer) param.get("tid0");

        Integer[] a= new Integer[]{tid0, tid1, tid2, tid3, tid4, tid5, tid6, tid7, tid8, tid9};
        int count=personalService.deleteUserTagByUId(uid);

        int count2=personalService.insertUserByUidAndArray(a,uid);
        map.put("success",true);
        return map;
    }

    @GetMapping("/Personal/tupian")
    private @ResponseBody Object getPhoto(@RequestParam Map param){
        Map<String,Object> map=new HashMap<>();

        String uid= (String) param.get("uid");
        List<UserImg> userImgs=personalService.getUserImgByUid(uid);
        if(userImgs!=null){
            map.put("success",true);
            map.put("img",userImgs);
            return map;
        }
        map.put("success",false);
        return map;
    }

    @PostMapping("/Personal/tu")
    private @ResponseBody Object savePhoto(UserTag userTag, @RequestParam("img") MultipartFile files[]){
        Map<String,Object> map=new HashMap<>();

        Integer uid=userTag.getUid();
        String fileName=null;
        for(int i=0;i<files.length;i++){
            String fileNameOrigin = files[i].getOriginalFilename();  // 文件名
            String uuidstr=UUID.randomUUID().toString();
            String uuid=uuidstr.substring(0, 8) + uuidstr.substring(9, 13) + uuidstr.substring(14, 18) + uuidstr.substring(19, 23) + uuidstr.substring(24, 36);
            fileName=fileNameOrigin.substring(0, fileNameOrigin.lastIndexOf("."))+"_"+uuid+".jpg";
            System.out.println(fileName);
            File dest = new File(uploadFilePath +'/'+ fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(dest);
            } catch (Exception e) {
                map.put("success",2);
                map.put("result","程序错误，请重新上传");
                return map;
            }
        }

        int count=personalService.insertUserImgByUid(uid,fileName);
        if(count==1){
            map.put("success",true);
        }
        return map;
    }

    @PostMapping("/Personal/to")
    private @ResponseBody Object saveTouxiang(UserTag userTag, @RequestParam("img") MultipartFile files[]){
        Map<String,Object> map=new HashMap<>();

        Integer uid=userTag.getUid();
        String fileName=null;
        for(int i=0;i<files.length;i++){
            String fileNameOrigin = files[i].getOriginalFilename();  // 文件名
            String uuidstr=UUID.randomUUID().toString();
            String uuid=uuidstr.substring(0, 8) + uuidstr.substring(9, 13) + uuidstr.substring(14, 18) + uuidstr.substring(19, 23) + uuidstr.substring(24, 36);
            fileName=fileNameOrigin.substring(0, fileNameOrigin.lastIndexOf("."))+"_"+uuid+".jpg";
            System.out.println(fileName);
            File dest = new File(uploadFilePath +'/'+ fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(dest);
            } catch (Exception e) {
                map.put("success",2);
                map.put("result","程序错误，请重新上传");
                return map;
            }
        }

        int count=personalService.updateUseravatarByUid(uid,fileName);
        if(count==1){
            map.put("success",true);
        }
        return map;

    }

    @PostMapping("/Personal/money")
    private @ResponseBody Object modifyMoney(@RequestBody Map param){
        Map<String,Object> map=new HashMap<>();
        float money_add= Float.parseFloat((String) param.get("money"));
        String uid= String.valueOf(param.get("uid"));
        User user=personalService.getUserByUid(uid);
        float money_origin=user.getMoney();
        float money=money_origin+money_add;
        int count=personalService.modifyUserMoneyByUid(Integer.valueOf(uid),money);
        map.put("success",money);
        return map;
    }
}
