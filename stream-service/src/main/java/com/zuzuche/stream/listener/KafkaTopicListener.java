package com.zuzuche.stream.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @desc: kafka topic 监听器
 * @author: panqiong
 * @date: 2018/8/8
 */
@Component
public class KafkaTopicListener {
    Logger logger = LoggerFactory.getLogger(KafkaTopicListener.class);

    /**
     * 监听 SOURCE_TOPIC 消息
     * @param consumer
     */
    @KafkaListener(topics = "SOURCE_TOPIC")
    public void receive(ConsumerRecord<?, ?> consumer) {

        logger.info("{} - {}:{}", consumer.topic(), consumer.key(), consumer.value());
    }




}
