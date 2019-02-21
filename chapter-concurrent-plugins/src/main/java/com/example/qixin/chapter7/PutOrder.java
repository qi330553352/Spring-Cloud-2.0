package com.example.qixin.chapter7;

import java.util.concurrent.DelayQueue;

/** 将订单推入延时队列
 * 创  建   时  间： 2019/2/22 0:41
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class PutOrder implements Runnable {

    private DelayQueue<ItemVo<Order>> queue;

    public PutOrder(DelayQueue<ItemVo<Order>> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        //5秒后超时
        Order orderTb = new Order("TB123456789",300);
        ItemVo<Order> itemTb = new ItemVo<Order>(5000,orderTb);
        queue.offer(itemTb);
        System.out.println("PutOrder5秒后超时："+orderTb.getOrderNo()+":"
                +orderTb.getOrderMoney());
        //3秒后超时
        Order orderJd = new Order("JD987654321",289);
        ItemVo<Order> itemJd = new ItemVo<Order>(3000,orderJd);
        queue.offer(itemJd);
        System.out.println("PutOrder3秒后超时："+orderJd.getOrderNo()+":"+orderJd.getOrderMoney());

    }
}
