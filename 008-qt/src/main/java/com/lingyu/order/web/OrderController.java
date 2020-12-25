package com.lingyu.order.web;


import com.lingyu.login.model.RecordActAndActivity;
import com.lingyu.login.model.User;
import com.lingyu.order.service.OrderService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/Order/getData")
    private @ResponseBody Object GetData(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        String now=request.getParameter("now")==null?"":request.getParameter("now");
        if(now.equals("")){
            map.put("errcode",10002);
            map.put("errmsg","获取不到订单类型");
            return map;
        }
        User user= (User) request.getSession().getAttribute("account");
        if(user==null){
            map.put("errcode",10001);
            map.put("errmsg","非法请求");
            return map;
        }
        String nowPage_s=request.getParameter("nowPage")==null?"1":request.getParameter("nowPage");
        String maxRow_s=request.getParameter("maxRow")==null?"5":request.getParameter("maxRow");
        Integer total=request.getParameter("total")=="0"?0: Integer.valueOf(request.getParameter("total"));
        Integer uid=user.getUid();
        Date date=new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime=sdf.format(date);
        System.out.println(nowTime);
        String sql="";
        List<RecordActAndActivity> orderList=null;
        if(nowPage_s.equals("1")){
            int count=orderService.modifyRecordStateByUidAndStateAndNowTime(uid,nowTime);
            if(now.equals("paid")){ sql="已支付";}
            if(now.equals("unpaid")){ sql="未支付";}
            if(now.equals("uncom")){ sql="待评价";}
            if(now.equals("all")){
                total=orderService.countRecordActByUid(uid);
                //订单表和活动表关联，查询出和存在的活动相关的、当前用户有关的订单,返回信息总数
            }else if(now.equals("qt")){
                total=orderService.countRecordActByUidAndState(uid); //除去已支付，未支付，待评价的当前用户的订单数
            }else {
                total=orderService.countRecordActByUidAndOneState(uid,sql);
            }
        }if(total!=0){
            int count=orderService.modifyRecordStateByUidAndStateAndNowTime(uid,nowTime);
            if(now.equals("paid")){ sql="已支付";}
            if(now.equals("unpaid")){ sql="未支付";}
            if(now.equals("uncom")){ sql="待评价";}
            Integer maxRow=Integer.valueOf(maxRow_s);
            Integer nowPage=Integer.valueOf(nowPage_s);
            Integer index=(nowPage-1)*maxRow;
            if(now.equals("all")){
                orderList=orderService.getAllOrder(uid,index,maxRow);
            }else if(now.equals("qt")){
                orderList=orderService.getOrderByQt(uid,index,maxRow);
            }else {
                orderList=orderService.getOrderBySql(uid,index,maxRow,sql);
            }
        }
        Integer totalPage=0;
        if(total%Integer.valueOf(maxRow_s)==0){
            totalPage = (int)Math.ceil(total/Integer.valueOf(maxRow_s));
        }else {
            totalPage = (int)Math.ceil(total/Integer.valueOf(maxRow_s))+1;
        }
        map.put("success",true);
        map.put("orderList",orderList);
        map.put("totalPage",totalPage);
        map.put("total",total);
        return map;
    }
}
