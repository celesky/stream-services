package com.zuzuche.stream.processor.bindings;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

/**
 * @desc: 输入输出topic绑定
 * @author: panqiong
 * @date: 2018/8/10
 */
public class Bindings {


    public interface Aggregate{
        String IN ="aggregateIn"; // 输入
        String OUT ="aggregateOut"; // 输出


        @Input(IN)
        KStream<?, ?> aggregateIn();

        @Output(OUT)
        KStream<?, ?> aggregateOut();


    }


    public interface Simulate{
        String IN ="simulateIn"; // routeProcessor的流入口


        @Output(IN)
        KStream<?, ?> simulateIn();

    }


}
