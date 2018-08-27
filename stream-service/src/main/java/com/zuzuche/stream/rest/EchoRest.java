package com.zuzuche.stream.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc:
 * @author: panqiong
 * @date: 2018/8/27
 */
@RestController
@RequestMapping("/")
@Slf4j
public class EchoRest {

    @RequestMapping(value = "/echo" , method = RequestMethod.GET)
    public String echo(){
        log.info("received echo.........");
        return "hello echo.........";
    }
}
