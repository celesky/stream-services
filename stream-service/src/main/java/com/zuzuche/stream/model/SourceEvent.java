package com.zuzuche.stream.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc: 源事件
 * @author: panqiong
 * @date: 2018/8/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SourceEvent {
    // 监控计划id--->比如 sms监控计划
    private String metricGroupId;
    // 统计指标 如:成功/失败/无状态
    private String metric;
    // 事件事件
    private long timestamp;// 事件发生事件 毫秒
    // 事件数量
    private int count; //默认1 也可以设值 因为原始数据可能是批量的


}
