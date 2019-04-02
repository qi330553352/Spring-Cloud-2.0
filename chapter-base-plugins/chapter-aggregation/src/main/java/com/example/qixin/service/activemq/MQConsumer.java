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
public class MQConsumer {

    @JmsListener(destination = "neo.queue", containerFactory = "queueListenerFactory")
    public void receiveQueue(String text) {
        System.out.println("Consumer queue msg : "+text);
    }

    @JmsListener(destination = "neo.topic", containerFactory = "topicListenerFactory")
    public void receiveTopic(String text) {
        System.out.println("Consumer topic msg : "+text);
    }
}
