package com.example.qixin.simplemsg;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

public class Consumer {

    private static DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
    private static int initialState = 0;

    private Consumer() {
    }

    public static DefaultMQPushConsumer getDefaultMQPushConsumer(){
        if(consumer==null)
            consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        if(initialState==0){
            consumer.setNamesrvAddr("192.168.147.132:9876");
            //这里设置的是一个consumer的消费策略
            //CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
            //CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
            //CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            initialState = 1;
        }
        return consumer;
    }
}
