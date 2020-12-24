package com.lingyu.config;


import com.lingyu.activity.service.ActivityService;
import com.lingyu.login.model.RecordAct;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpirationListener(RedisMessageListenerContainer container) {
        super(container);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Autowired
    private RedisTemplate<String,Object> template;

    @Autowired
    private ActivityService activityService;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();//生效的key

        String actid= (String) template.opsForValue().get(key+"_s");
        String uid= (String) template.opsForValue().get(actid);
        RecordAct recordAct=activityService.getRecordActByUidAndActId(uid,actid);
        if(!recordAct.getState().equals("已支付") ){
            int count=activityService.modifyRecordStateByUidAndActId(uid,actid);
        }

        template.opsForHash().delete(uid,actid);
        template.delete(actid);
        template.delete(key+"_s");

        System.out.println(key);
    }
}






