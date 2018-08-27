package com.zuzuche.stream.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @desc: 每个时间窗口的聚合结果
 * @author: panqiong
 * @date: 2018/8/10
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggreStats {

    // 按照planId分组 聚合
    String metricGroupId; // 所属监控计划
    // metric-->count 计划对应的监控指标 以及 总数
    Map<String,AtomicLong> metricCountMap = new ConcurrentHashMap<>();
    // 用于存放计算后的 指标 以及 百分比
    // metric-->percent
    Map<String,Double> metricPercentMap = new ConcurrentHashMap<>();;

    // 维护一个总数 方便后续计算百分比
    long sumCount ;

    /**
     * 提供了一个方法对记录进行聚合
     * @param event
     * @return
     */
    public AggreStats add(SourceEvent event) {
        try{
            // log.info(">>>>>"+event.toString());
            if (event.getMetricGroupId() == null || event.getMetric() == null)
                throw new IllegalArgumentException("Invalid event to aggregate: " + event.toString());

            if (this.metricGroupId == null){
                this.metricGroupId = event.getMetricGroupId();
            }

            // planId不一致
            if(!this.metricGroupId.equals(event.getMetricGroupId())){
                throw new IllegalArgumentException("Aggregating stats for planId  " + this.metricGroupId +  " but recieved trade of metricGroupId " + event );
            }

//        // 如果已经有这个指标的数据 对其累加
            if (this.metricCountMap.containsKey(event.getMetric())){
                metricCountMap.get(event.getMetric()).getAndAdd(event.getCount());
            }else{
                // 初始化一个0进去
                metricCountMap.putIfAbsent(event.getMetric(),new AtomicLong(0));
                metricCountMap.get(event.getMetric()).getAndAdd(event.getCount());
            }
            //累加总数
            sumCount = this.sumCount+event.getCount();
        }catch (Exception e){
            e.printStackTrace();
        }

        //log.info(">>>>>"+this.toString());
        return this;
    }

    /**
     * 计算百分比
     * @return
     */
    public AggreStats computePercent() {
        metricCountMap.forEach((k, v)->{
            double percent = (double) Math.round(v.doubleValue()*100/(double)sumCount)/100;
            metricPercentMap.put(k,percent);
        });
        log.info(">>>>>"+this.toString());
        return this;
    }


    public Double getMetricPercent(String metric){
        return metricPercentMap.get(metric);
    }

    public static void main(String[] args) {
        double a = 123;
        double b = 30;
        //double per = (double) Math.round(b*100/a)/100;
        double per = (double) Math.round(b*100/a)/100;

        System.out.println("" + per);
    }


}
