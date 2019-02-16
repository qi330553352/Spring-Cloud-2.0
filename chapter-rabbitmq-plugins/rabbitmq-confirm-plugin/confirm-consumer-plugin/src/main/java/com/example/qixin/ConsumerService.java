package com.example.qixin;

import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 创  建   时  间： 2019/2/13 0:34
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Service
public class ConsumerService {

    @RabbitListener(queues = "qixin-queue-test-1")
    public void process(Message message, Channel channel) throws IOException, InterruptedException {
        // 采用手动应答模式, 手动确认应答更为安全稳定;第二个参数multiple表示是否批量应答，很明显现在不是批量应答
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
        channel.basicQos(1);//该消费者在接收到队列里的消息但没有返回确认结果之前,它不会将新的消息分发给它。
        Thread.sleep(1000L);
        log.info(deliveryTag+":1receive1: " + new String(message.getBody()));
    }

    @RabbitListener(queues = "qixin-queue-test-1")
    public void process2(Message message, Channel channel) throws IOException {
        // 采用手动应答模式, 手动确认应答更为安全稳定;第二个参数multiple表示是否批量应答，很明显现在不是批量应答
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
        log.info(deliveryTag+":2receive2: " + new String(message.getBody()));
    }
}
