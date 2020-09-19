package com.gy.kafka.consumer;

import com.alibaba.fastjson.JSONObject;
import com.gy.entry.Message;
import com.gy.kafka.topic.KafkaTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {KafkaTopic.TEST_TOPIC}, groupId = "myGroup")
    public void testListen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();

            log.info("kafka consumer message:{}" , message);
            Message data = JSONObject.parseObject((String)message, Message.class);
            String jsonStr = data.getMsg().toString();
            log.info("接收到的参数打印 data:{}",jsonStr);

        }
    }
}
