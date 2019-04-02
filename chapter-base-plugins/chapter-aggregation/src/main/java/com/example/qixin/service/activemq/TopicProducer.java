package com.example.qixin.service.activemq;

import org.apache.activemq.broker.region.Topic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 创 建 时 间: 2019/4/3
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@Component
public class TopicProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQTopic topic;

    public void sendTopic(String msg) {
        System.out.println("send topic msg :"+msg);
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }
}
