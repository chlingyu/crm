package com.lingyu.config;


import com.lingyu.activity.service.ActivityService;
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
        ValueOperations<String, Object> val=template.opsForValue();
        String actid= (String) val.get(key+"_s");
        String uid= (String) val.get(actid);
        template.opsForHash().delete(uid,actid);
        template.delete(actid);
        template.delete(key+"_s");
        System.out.println(key);
        int count=activityService.modifyRecordStateByUidAndActId(uid,actid);

    }
}






