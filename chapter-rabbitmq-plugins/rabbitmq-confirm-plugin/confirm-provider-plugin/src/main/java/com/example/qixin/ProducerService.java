package com.example.qixin;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创  建   时  间： 2019/2/12 23:22
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 给hello队列发送消息
     */
    public void send() {
        for (int i =0; i< 50; i++) {
            String msg = "hello, 序号: " + i;
            log.info("Producer, " + msg);
            rabbitTemplate.convertAndSend("qixin-queue-test-1", msg);
        }
    }
}
