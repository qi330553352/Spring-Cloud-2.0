package com.example.qixin.config;

import org.apache.activemq.broker.region.Topic;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创 建 时 间: 2019/4/3
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@Configuration
public class ActivemqConfig {

    @Bean
    public ActiveMQQueue queue() {

        return new ActiveMQQueue("neo.queue");
    }

    @Bean
    public ActiveMQTopic topic() {

        return new ActiveMQTopic("neo.topic");
    }
}
