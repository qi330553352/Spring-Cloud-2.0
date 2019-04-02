package com.example.qixin.service.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 创 建 时 间: 2019/4/3
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@Component
public class Consumer2 {

    @JmsListener(destination = "neo.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer2 queue msg : "+text);
    }
}
