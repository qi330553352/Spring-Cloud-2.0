package com.example.qixin.simplemsg;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

/**
 * 生产者
 */
public class Producer {

    private static DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
    private static int initialState = 0;

    private Producer() {

    }

    public static DefaultMQProducer getDefaultMQProducer(){
        if(producer==null)
            producer = new DefaultMQProducer("ProducerGroupName");
        if(initialState==0){
            //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
            producer.setNamesrvAddr("192.168.147.132:9876");
            try {
                producer.start();
            } catch (MQClientException e) {
                e.printStackTrace();
                return null;
            }
            initialState = 1;
        }
        return producer;
    }


}
