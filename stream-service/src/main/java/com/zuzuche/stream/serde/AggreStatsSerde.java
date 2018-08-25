package com.zuzuche.stream.serde;

import com.zuzuche.stream.model.AggreStats;
import com.zuzuche.stream.serde.base.JsonDeserializer;
import com.zuzuche.stream.serde.base.JsonSerializer;
import com.zuzuche.stream.serde.base.WrapperSerde;

/**
 * @desc:
 * @author: panqiong
 * @date: 2018/8/18
 */
public class AggreStatsSerde extends WrapperSerde<AggreStats> {
    public AggreStatsSerde() {
        super(new JsonSerializer<AggreStats>(), new JsonDeserializer<AggreStats>(AggreStats.class));
    }
}