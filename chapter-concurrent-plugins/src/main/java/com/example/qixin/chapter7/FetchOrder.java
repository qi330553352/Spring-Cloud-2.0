package com.example.qixin.chapter7;

import java.util.concurrent.DelayQueue;

/** 检测延时队列，取出到期的订单
 * 创  建   时  间： 2019/2/22 0:43
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class FetchOrder implements Runnable {

    private DelayQueue<ItemVo<Order>> queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                ItemVo<Order> item = queue.take();
                Order order = (Order)item.getData();
                System.out.println("GetFromQueue："+" data = "+order.getOrderNo()+"-"+order.getOrderMoney());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
