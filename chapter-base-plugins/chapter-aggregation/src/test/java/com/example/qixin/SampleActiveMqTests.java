package com.example.qixin;

import com.example.qixin.service.activemq.Producer;
import com.example.qixin.service.activemq.TopicProducer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 创 建 时 间: 2019/4/3
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleActiveMqTests {

    @Autowired
    private Producer producer;
    @Autowired
    private TopicProducer topicProducer;
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void sendSimpleQueueMessage() throws InterruptedException {
        this.producer.sendQueue("Test queue message");
        Thread.sleep(1000L);
        assertThat(this.outputCapture.toString().contains("Test queue")).isTrue();
    }

    @Test
    public void send100QueueMessage() throws InterruptedException {
        for (int i=0;i<100;i++){
            this.producer.sendQueue("Test queue message"+i);
        }
        Thread.sleep(1000L);
    }

    @Test
    public void sendSimpleTopicMessage() throws InterruptedException {
        this.topicProducer.sendTopic("Test Topic message");
        Thread.sleep(1000L);
    }
}
