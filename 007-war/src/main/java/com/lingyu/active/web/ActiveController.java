package com.lingyu.active.web;



import com.lingyu.active.model.Activity;
import com.lingyu.active.model.ActivityReceive;
import com.lingyu.active.service.ActiveService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Handler;

@Controller
@Slf4j
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    @Value("${file.upload.url}")
    private String uploadFilePath;

    @RequestMapping("/Active/getActive")
    private @ResponseBody Object getActiveList(){
        List<Activity> activityList=activeService.getActivityList();
        return activityList;
    }

    @PostMapping("/Relact/saveAct")
    private @ResponseBody Object test(ActivityReceive activity,@RequestParam("actImg") MultipartFile files[]) throws ParseException, IOException {
        Map<String,Object> map = new HashMap<>();

        String beginTime=activity.getBeginTime()+" 00:00:00";
        String postEnd=activity.getPostEnd()+" 00:00:00";
        String postBegin=activity.getPostBegin()+" 00:00:00";

        long nowTime=System.currentTimeMillis();
        System.out.println(nowTime);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long beginTime_d=sdf2.parse(beginTime).getTime();
        long postEnd_d=sdf2.parse(postEnd).getTime();
        long postBegin_d=sdf2.parse(postBegin).getTime();

        if(nowTime>postBegin_d){
            map.put("errcode",10000);
            map.put("errmsg","报名开始时间应该在当前时间之后！");
            return map;
        }if(postEnd_d<postBegin_d){
            map.put("errcode",10001);
            map.put("errmsg","报名结束时间应设置于报名开始时间之后！");
            return map;
        }if(beginTime_d<postEnd_d){
            map.put("errcode",10002);
            map.put("errmsg","活动时间应设置于报名结束时间之后！");
            return map;
        }

        Integer lastTime=activity.getLastTime();
        long endTime_d=beginTime_d+(lastTime*86400*1000); //时间戳单位是毫秒，切记
        System.out.println(beginTime_d);
        System.out.println(endTime_d);


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = sdf.format(endTime_d);
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
                log.error("{}",e);
                map.put("success",2);
                map.put("result","程序错误，请重新上传");
                return map;
            }
        }

        Integer actPrice=activity.getActPrice();
        String actName=activity.getActName();
        String actImg=fileName;
        String lng=activity.getLng();
        String lat=activity.getLat();
        String actDetail=activity.getActDetail();
        String actCity=activity.getActCity();
        String actAddress=activity.getActAddress();
        Integer actNum=activity.getActNum();
        Activity ac=new Activity();
        ac.setActPrice(actPrice);
        ac.setActName(actName);
        ac.setLastTime(lastTime);
        ac.setActImg(actImg);
        ac.setLng(lng);
        ac.setLat(lat);
        ac.setActDetail(actDetail);
        ac.setActCity(actCity);
        ac.setActAddress(actAddress);
        ac.setActNum(actNum);
        ac.setBeginTime(beginTime);
        ac.setEndTime(endTime);
        ac.setPostBegin(postBegin);
        ac.setPostEnd(postEnd);

        int count=activeService.test(ac);
        System.out.println(count);
        if(count==1){
            map.put("success",true);
        }
        return map;

    }
}
