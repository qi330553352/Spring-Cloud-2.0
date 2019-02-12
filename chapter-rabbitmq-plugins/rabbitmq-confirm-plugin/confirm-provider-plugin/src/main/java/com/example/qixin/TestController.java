package com.example.qixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 创  建   时  间： 2019/2/12 23:21
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/index")
    public Map<String,Object> index(){
        Map<String,Object> map = new HashMap<>();
        producerService.send();
        map.put("success",true);
        return map;
    }
}
