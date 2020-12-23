package com.lingyu.activity.web;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.lingyu.activity.service.ActivityService;
import com.lingyu.login.model.*;
import com.lingyu.util.DateUtil;
import com.lingyu.util.JWTUtils;
import com.lingyu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RedisTemplate<String,Object> template;

    @GetMapping("/Activity/newAct")
    private @ResponseBody Object getNewAct(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();

        String nowPage=request.getParameter("nowPage")==null?"1":request.getParameter("nowPage");
        String city=request.getParameter("city")==null?"":request.getParameter("city");
        String keywords=request.getParameter("keywords")==null?"":request.getParameter("keywords");
        String maxRow_s=request.getParameter("maxRow")==null?"6":request.getParameter("maxRow");
        String total=request.getParameter("total")==null?"0":request.getParameter("total");

        Date date=new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime=sdf.format(date);
        System.out.println(nowTime);
        if(nowPage.equals("1")){
            total= String.valueOf(activityService.countAct(nowTime,city,keywords));
        }

        Integer per=(Integer.valueOf(nowPage)-1)*Integer.valueOf(maxRow_s);
        List<Activity> actList=null;
        Integer maxRow= Integer.valueOf(maxRow_s);
        if(!total.equals("0")){
           actList=activityService.getAct(nowTime,city,keywords,per,maxRow);
        }else {
            actList=null;
        }
        Integer totalPage=0;
        if(Integer.valueOf(total)%maxRow==0){
            totalPage=(int)Math.ceil(Integer.valueOf(total)/maxRow);
        }else{
            totalPage=(int)Math.ceil(Integer.valueOf(total)/maxRow)+1;
        }

        map.put("success",true);
        map.put("actList",actList);
        map.put("totalPage",totalPage);
        map.put("total",Integer.valueOf(total));
        return map;
    }

    @GetMapping("/Activity/oldAct")
    private @ResponseBody Object getOldAct(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        Integer nowPage=request.getParameter("nowPage")==null?1:Integer.valueOf(request.getParameter("nowPage"));
        Integer maxRow=request.getParameter("maxRow")==null?6:Integer.valueOf(request.getParameter("maxRow"));
        Integer total=request.getParameter("total")==null?0:Integer.valueOf(request.getParameter("total"));

        Date date=new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime=sdf.format(date);
        System.out.println(nowTime);
        if(nowPage==1){
            total=activityService.countOldAct(nowTime);
        }
        Integer per=(nowPage-1)*maxRow;
        List<Activity> actList=null;
        if(total!=0){
            actList=activityService.getOldAct(nowTime,per,maxRow);
        }else{
            actList=null;
        }
        Integer totalPage=0;
        if(Integer.valueOf(total)%maxRow==0){
            totalPage=(int)Math.ceil(Integer.valueOf(total)/maxRow);
        }else{
            totalPage=(int)Math.ceil(Integer.valueOf(total)/maxRow)+1;
        }
        map.put("success",true);
        map.put("actList",actList);
        map.put("totalPage",totalPage);
        map.put("total",total);
        return map;
    }

    @GetMapping("/Activity/getData")
    private @ResponseBody Object getData(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        String actid=request.getParameter("actid")==null?"":request.getParameter("actid");
        if(actid.equals("")){
            map.put("errcode",10001);
            map.put("errmsg","无法获得活动序号");
            return map;
        }
        Activity act=activityService.getActByActid(actid);
        if(act==null){
            map.put("errcode",10001);
            map.put("errmsg","无法获取到该活动序号详细信息！");
            return map;
        }

        long stampBegin= act.getBeginTime().getTime();
        long stampEnd=act.getEndTime().getTime();
        long postBegin=act.getPostBegin().getTime();
        long postEnd=act.getPostEnd().getTime();
        long now=System.currentTimeMillis();
        String type="";
        if(postEnd < now){
            type = "已无法购票";
        }if(postBegin > now){
            type = "尚未开始";
        }
        if(postBegin < now && postEnd > now) type = "立即参与";

        String token=request.getParameter("token");
        DecodedJWT decodedJWT=JWTUtils.verify(token);
        String uid=decodedJWT.getClaim("uid").asString();

        RecordAct res=activityService.getRecordActByUidAndActId(uid,actid);  //当前登录用户对该活动的参与状态（未支付/已支付）
        if(res!=null){
            if(res.getState().equals("未支付")&&type.equals("立即参与")){
                type="您已下单";
            }if(res.getState().equals("已支付")&&type.equals("立即参与")){
                type="参与成功";
            }
        }

        String actCity=act.getActCity();
        List<Activity> actArr=activityService.getActBySimilarCity(actid,actCity); //相同城市的其他活动
        List<UserAndRecordAct> userList=activityService.getUserByActIdAndState(actid); //对此活动做出评价的所有用户相关信息
        Integer total=activityService.countComActByActId(actid); //此活动所有评论的数量
        List<UserAndComAct> comArr=null;
        if(total!=0){
            comArr=activityService.getUserAndComActByActId(actid);
        }
        Integer totalPage=0;
        if(total%6==0){
            totalPage=(int)Math.ceil(Integer.valueOf(total)/6);
        }else{
            totalPage=(int)Math.ceil(Integer.valueOf(total)/6)+1;
        }

        map.put("success",true);
        map.put("act",act);
        map.put("actArr",actArr);
        map.put("userList",userList);
        map.put("type",type);
        map.put("total",total);
        map.put("comArr",comArr);
        map.put("totalPage",totalPage);
        return map;
    }


    @GetMapping("/Activity/buy")
    private @ResponseBody Object buy(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        User user= (User) request.getSession().getAttribute("account");
        if(user==null){
            map.put("errcode",10001);
            map.put("errmsg","用户不存在");
            return map;
        }
        String uid= String.valueOf(user.getUid());
        String actid=request.getParameter("actid");


       ValueOperations<String, Object> val=template.opsForValue();

        String orderId= (String) template.opsForHash().get(uid,actid);  //从哈希数据中根据用户id,活动id取得订单号
        if(orderId==null){ //订单号为空，说明还没有参与活动，从而没有订单id信息
            orderId=UUIDUtil.get16UUID(); //用工具包生成16位唯一订单号*/
            template.opsForHash().put(uid,actid,orderId); //在hash数据中生成 用户id，活动id，订单id的对应关系
            val.set(orderId,actid,5,TimeUnit.SECONDS);
            val.set(orderId+"_s",actid); //订单id和活动id对应关系(为了在监听器得到actid)
            val.set(actid,uid);  //活动id和用户id对应关系(为了在监听器得到uid)
            //同时给value数据绑定订单id和活动id的对应关系，设置订单id存活时间，一旦过期，监听器能得到想要的过期订单id
            request.getSession().setAttribute("actid",actid);
            int count=activityService.addRecordAct(orderId,uid,actid); //同时给mysql订单添加一笔交易记录，交易转态是创建交易，因此是未支付
            map.put("success",true);
            map.put("ordetid",orderId);
            return map;
        }else {
            map.put("errcode",10002);
            map.put("errmsg","您已经参与该活动了！");
            return map;
        }

    }

}
