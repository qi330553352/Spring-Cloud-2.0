package com.example.qixin.simplemsg;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class SimpleTest {

    public static void main(String[] args)throws Exception{
        //sendMsg();
        getMsg();
    }

    private static void getMsg()throws Exception{
        DefaultMQPushConsumer consumer = Consumer.getDefaultMQPushConsumer();
        //设置consumer所订阅的Topic和Tag，*代表全部的Tag
        consumer.subscribe("TopicTest1", "*");
        //设置一个Listener，主要进行消息的逻辑处理
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
                //返回消费状态
                //CONSUME_SUCCESS 消费成功
                //RECONSUME_LATER 消费失败，需要稍后重新消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //调用start()方法启动consumer
        consumer.start();
        System.out.println("Consumer Started.");
    }

    private static void sendMsg(){
        DefaultMQProducer producer = Producer.getDefaultMQProducer();
        try{
        for(int i=0;i<10;i++){
            Message message = new Message("TopicTest1","TagA","OrderId00"+i,("Hello msg"+i).getBytes());
            //这里调用的是同步的方式，所以会有返回结果
            SendResult result = producer.send(message);
            log.info("result:"+result);
        }}catch (Exception e){
            e.printStackTrace();
        }
        //发送完消息之后，调用shutdown()方法关闭producer
        producer.shutdown();
    }

}
