package com.example.qixin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
/*
同时支持队列（Queue）和广播（Topic）

Spring Boot 集成 ActiveMQ 的项目默认只支持队列或者广播中的一种，通过配置项 spring.jms.pub-sub-domain 的值来控制，
true 为广播模式，false 为队列模式，默认情况下支持队列模式。

如果需要在同一项目中既支持队列模式也支持广播模式，可以通过 DefaultJmsListenerContainerFactory 创建自定义的
JmsListenerContainerFactory 实例，之后在 @JmsListener 注解中通过 containerFactory 属性引用它。

分别创建两个自定义的 JmsListenerContainerFactory 实例，通过 pubSubDomain 来控制是支持队列模式还是广播模式。


 */

/**
 * 创 建 时 间: 2019/4/3
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@Configuration
@EnableJms
public class MQConfig {

    @Bean("queueListenerFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean("topicListenerFactory")
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
