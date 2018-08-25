package com.zuzuche.stream.serde;

import com.zuzuche.stream.model.SourceEvent;
import com.zuzuche.stream.serde.base.JsonDeserializer;
import com.zuzuche.stream.serde.base.JsonSerializer;
import com.zuzuche.stream.serde.base.WrapperSerde;

/**
 * @desc:
 * @author: panqiong
 * @date: 2018/8/18
 */
public class SourceEventSerde  extends WrapperSerde<SourceEvent> {
    public SourceEventSerde() {
        super(new JsonSerializer<SourceEvent>(), new JsonDeserializer<SourceEvent>(SourceEvent.class));
    }
}
