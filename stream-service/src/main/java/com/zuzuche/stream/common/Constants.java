package com.zuzuche.stream.common;

/**
 * @desc:
 * @author: panqiong
 * @date: 2018/8/25
 */
public class Constants {
    public static final String[] TICKERS = {"MMM", "ABT", "ABBV", "ACN", "ATVI", "AYI", "ADBE", "AAP", "AES", "AET"};
    public static final String[] indecators = {"success", "failure"};
    public static final String[] plans = {"101", "102","103"};
    public static final int START_PRICE = 5000;
    public static final int DELAY = 100; // sleep in ms between sending "asks"
    //public static final String BROKER =  "47.106.140.44:9092";//192.168.100.94:9092,192.168.100.96:9093,192.168.100.96:9094";
    public static final String BROKER =  "192.168.100.94:9092,192.168.100.96:9093,192.168.100.96:9094";//192.168.100.94:9092,192.168.100.96:9093,192.168.100.96:9094";
}
