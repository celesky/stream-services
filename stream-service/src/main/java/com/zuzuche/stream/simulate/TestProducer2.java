package com.zuzuche.stream.simulate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuzuche.stream.common.Constants;
import com.zuzuche.stream.model.SourceEvent;
import com.zuzuche.stream.serde.base.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * @desc: 模拟生产者
 * @author: panqiong
 * @date: 2018/8/12
 */
public class TestProducer2 {

    public static KafkaProducer<String, String> producer = null;

    public static void main(String[] args) throws Exception {

        System.out.println("Press CTRL-C to stop generating data");


        // add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutting Down");
                if (producer != null)
                    producer.close();
            }
        });
        ObjectMapper mapper = new ObjectMapper();
        JsonSerializer<SourceEvent> sourceEventSerde = new JsonSerializer<>();

        //JsonSerializer<SourceEvent> eventSerializer = new JsonSerializer<>();

        // Configuring producer
        Properties props = new Properties();

        props.put("bootstrap.servers", Constants.BROKER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Starting producer
        producer = new KafkaProducer<>(props);

        long iter = 0;

        // Start generating events, stop when CTRL-C
        String[] randomWords = new String[]{"foo", "bar", "foobar", "baz", "fox"};
        while (true) {
            iter++;
            for (String word : randomWords) {

                // Note that we are using ticker as the key - so all asks for same stock will be in same partition
                ProducerRecord<String, String> record = new ProducerRecord<>("words", "123", word);

                producer.send(record, (RecordMetadata r, Exception e) -> {
                    System.out.println("send ticker success:" + word);
                    if (e != null) {
                        System.out.println("Error producing to topic " + r.topic());
                        e.printStackTrace();
                    }
                });
            }
            Thread.sleep(Constants.DELAY);
        }
    }

}
