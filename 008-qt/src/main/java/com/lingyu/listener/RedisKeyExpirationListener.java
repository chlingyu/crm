package com.lingyu.listener;


import com.lingyu.activity.service.ActivityService;
import com.lingyu.login.model.RecordAct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;


@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Autowired
    private ActivityService activityService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public void onMessage(Message message, byte[] pattern) {

        String expiredKey = message.toString(); //得到过期的订单号
        System.out.println(expiredKey);
        String uid= (String) redisTemplate.opsForValue().get(expiredKey+"uid");
        String actid= (String) redisTemplate.opsForValue().get(expiredKey+"actid");
        RecordAct recordAct=activityService.getRecordActByUidAndActId(uid,actid);
        if(recordAct.getState().equals("未支付")){
            activityService.modifyRecordStateByUidAndActId(uid,actid);
            redisTemplate.delete(expiredKey+"uid");
            redisTemplate.delete(expiredKey+"actid");
            redisTemplate.opsForHash().delete(uid,actid);
        }
        /*if(uid!=null && actid!=null && recordAct.getState().equals("已支付")){
            int count=activityService.modifyRecordStateByUidAndActId(uid,actid);
        }*/
    }
}