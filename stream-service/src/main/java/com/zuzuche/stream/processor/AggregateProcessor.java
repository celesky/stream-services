package com.zuzuche.stream.processor;

import com.zuzuche.stream.model.AggreStats;
import com.zuzuche.stream.model.MoveWindow;
import com.zuzuche.stream.model.SourceEvent;
import com.zuzuche.stream.processor.bindings.Bindings;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @desc: 事件流处理器
 * @author: panqiong
 * @date: 2018/8/12
 */
@EnableBinding(Bindings.Aggregate.class)
public class AggregateProcessor {

    // 在数据库配置的窗口id
    private static Integer windowId = 1001;

    /**
     * @param source
     * @return
     */
    @StreamListener(Bindings.Aggregate.IN)
    @SendTo(Bindings.Aggregate.OUT)
    public KStream<MoveWindow, AggreStats> process(KStream<String, SourceEvent> source) {
        // 确保事件流按照记录的键进行分区。
        KStream<MoveWindow, AggreStats> statsKStream = source.groupByKey()
                .aggregate(
                        AggreStats::new,
                        (k, v, reduceStats) -> reduceStats.add(v), // 更新窗口内的计算结果 。
                        TimeWindows.of(60000).advanceBy(60000),    // 定义时间窗口
                        new JsonSerde<>(AggreStats.class),                   // 序列化和反序列化结果
                        "60-60-store"        // 本地状态存储名字
                )
                .toStream((key, value) -> new MoveWindow(key.key(),windowId, key.window().start(),key.window().end()))
                // 遍历values并进行计算百分比率
                .mapValues((reduceStats) -> reduceStats.computePercent());
        // 返回聚合后的数据流
        return statsKStream;
    }




}
