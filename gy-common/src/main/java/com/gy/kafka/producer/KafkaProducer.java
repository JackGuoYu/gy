package com.gy.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.gy.entry.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by guoyu on 2020/5/5
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

//    public void send(String topic, String msg){
//        kafkaTemplate.send(topic, msg);
//    }

    public void send(String topic, Object data) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(data);
        message.setSendTime(LocalDateTime.now());
        kafkaTemplate.send(topic, JSON.toJSONString(message));
    }

}
