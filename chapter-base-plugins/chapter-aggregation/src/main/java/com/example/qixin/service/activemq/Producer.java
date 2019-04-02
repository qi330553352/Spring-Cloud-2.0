package com.example.qixin.service.activemq;

import org.apache.activemq.broker.region.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * 创 建 时 间: 2019/4/3
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@Component
public class Producer {

    @Autowired
    private ActiveMQQueue queue;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendQueue(String msg) {
        System.out.println("send queue msg :"+msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}
