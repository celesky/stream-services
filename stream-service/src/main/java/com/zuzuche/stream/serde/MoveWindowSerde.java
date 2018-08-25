package com.zuzuche.stream.serde;

import com.zuzuche.stream.model.MoveWindow;
import com.zuzuche.stream.serde.base.JsonDeserializer;
import com.zuzuche.stream.serde.base.JsonSerializer;
import com.zuzuche.stream.serde.base.WrapperSerde;

/**
 * @desc:
 * @author: panqiong
 * @date: 2018/8/18
 */
public class MoveWindowSerde  extends WrapperSerde<MoveWindow> {
    public MoveWindowSerde() {
        super(new JsonSerializer<MoveWindow>(), new JsonDeserializer<MoveWindow>(MoveWindow.class));
    }
}
