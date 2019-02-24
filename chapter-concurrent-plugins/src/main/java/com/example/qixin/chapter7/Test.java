package com.example.qixin.chapter7;

import java.util.concurrent.DelayQueue;

/** 延时队列测试程序
 * 创  建   时  间： 2019/2/22 0:43
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<ItemVo<Order>> queue = new DelayQueue<>();

        new Thread(new PutOrder(queue)).start();
        new Thread(new FetchOrder(queue)).start();

        for(int i=1;i<15;i++){
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
