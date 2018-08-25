package com.zuzuche.stream.simulate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zuzuche.stream.common.Constants;
import com.zuzuche.stream.model.MoveWindow;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * @desc: 模拟消费者
 * @author: panqiong
 * @date: 2018/8/25
 */
@Slf4j
public class TestConsumer {


    /**
     * 自动提交偏移量
     */
    public void autoCommit(){
        Properties props = new Properties();
        props.put("bootstrap.servers", Constants.BROKER);
        props.put("group.id", "group4");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("aggre_stats_topic"));
        while (true) {

            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                //System.out.printf(new Date()+">> offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                //System.out.println(new Date()+">> record.toString() = " + record.toString());
                String key = record.key();
                //MoveWindow map = new JSONObject(key, MoveWindow.class);
                MoveWindow map = JSONObject.parseObject(key,MoveWindow.class);
                log.info(new Date(map.getStartTimestamp())+" "+ record.toString());

            }
        }
    }

    public static void main(String[] args) {
        new TestConsumer().autoCommit();
    }


}
