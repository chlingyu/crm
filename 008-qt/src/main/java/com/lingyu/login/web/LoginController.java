package com.lingyu.login.web;


import com.lingyu.login.model.User;
import com.lingyu.login.service.LoginService;
import com.lingyu.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("Identity/user")
    public @ResponseBody Map uer(){
        Map<String,Object> map=new HashMap<String, Object>();
        List<User> userList=loginService.getUser();
       if(userList!=null){
           map.put("success",userList);
       }
       return map;
    }


    /* 获取验证码图片*/

    @RequestMapping("/Identity/getCode")
    public Object getVerificationCode(HttpServletResponse response, HttpServletRequest request) {

        try {

            int width=200;

            int height=69;

            BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

            //生成对应宽高的初始图片

            String randomText = VerifyCode.drawRandomText(width,height,verifyImg);

            //单独的一个类方法，出于代码复用考虑，进行了封装。

            //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符

            request.getSession().setAttribute("verifyCode", randomText);

            response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别

            OutputStream os = response.getOutputStream(); //获取文件输出流

            ImageIO.write(verifyImg,"png",os);//输出图片流

            os.flush();

            os.close();//关闭流

        } catch (IOException e) {

            e.printStackTrace();

        }

        String verifyCode= (String) request.getSession().getAttribute("verifyCode");
        return verifyCode;
    }

    @PostMapping("/Identity/reg")
    public @ResponseBody Map register(@RequestBody Map<String,Object> param,HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        String captcha= (String) param.get("captcha");
        String phone= (String) param.get("phone");
        String pwd= (String) param.get("pwd");
        String sex=(String) param.get("sex");
        String compareToCaptcha= (String) request.getSession().getAttribute("verifyCode");
        if(!captcha.equals(compareToCaptcha)){
            map.put("errcode",1002);
            map.put("errmsg","验证码错误，请重新输入");
            return map;
        }
        User user=loginService.getUserByPhone(phone);
        if(user!=null){
            map.put("errcode",1);
            return map;
        }

        long time=System.currentTimeMillis();
        User insertUser=new User();
        if(sex.equals("1")){
            sex="男";
        }else {
            sex="女";
        }
        insertUser.setPhone(phone); insertUser.setPwd(pwd); insertUser.setStatus("使用"); insertUser.setSex(sex);
        insertUser.setIsResetPwd("否"); insertUser.setCharm("0");insertUser.setMoney(0f); insertUser.setAvatar("touxian1.jpg");
        insertUser.setUname(time+"");
        int countUser=loginService.insertUser(insertUser);

        User createUser=loginService.getUserByPhone(phone);
        Integer uid=createUser.getUid();
        int countUserInfo=loginService.insertUserInfo(uid,sex);
        //user-info表操作暂未完成
        if(countUserInfo==1){
            map.put("success",true);
            map.put("errmsg","注册成功");
        }
        return map;
    }

    @PostMapping("/Identity/log")
    public @ResponseBody Map login(@RequestBody Map<String,Object> param,HttpServletRequest request) {
        Map<String,Object> map=new HashMap<>();
        String phone= (String) param.get("phone");
        String pwd= (String) param.get("pwd");

        User userByPhone=loginService.getUserByPhone(phone);
        if(userByPhone==null){
            map.put("errocode","您尚未注册呢，亲");
            return map;
        }if(userByPhone.getStatus().equals("使用")){
            User userLogin=loginService.getUserByPhoneAndPwd(phone,pwd);
            if(userLogin!=null){
                Map<String,String> tokenMap=new HashMap<>();
                request.getSession().setAttribute("account",userLogin);

                tokenMap.put("phone",userLogin.getPhone());
                tokenMap.put("uid",userLogin.getUid()+"");
                String token= JWTUtils.creatToken(tokenMap);
               /* request.getSession().setAttribute("token",token);
                request.getSession().setAttribute("account",userLogin);*/
                map.put("success",true);
                map.put("user",token);
                return map;
            }
            map.put("errocode","密码错误");
            return map;
        }
        map.put("errocode","该账户涉嫌违规已被锁定，如有不便尽请谅解");
        return map;
    }


    @PostMapping("/Identity/seek")
    public @ResponseBody Map modifyPwd(@RequestBody Map<String,Object> param) {
        Map<String,Object> map=new HashMap<>();
        String phone= (String) param.get("phone");
        User user=loginService.getUserByPhone(phone);
        int count=0;
        if(user!=null){
            count=loginService.modifyResetPwd(phone);
            if(count==1){
                map.put("success",true);
            }
        }
        return map;
    }

}
