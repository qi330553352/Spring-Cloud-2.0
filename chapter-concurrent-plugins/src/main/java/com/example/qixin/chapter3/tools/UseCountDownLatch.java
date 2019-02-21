package com.example.qixin.chapter3.tools;

import com.example.qixin.tools.SleepTools;

import java.util.concurrent.CountDownLatch;

/** 演示CountDownLatch用法，共5个初始化子线程，6个闭锁扣除点，扣除完毕后，主线程和业务线程才能继续执行
 * 创  建   时  间： 2019/2/22 0:17
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseCountDownLatch {

    static CountDownLatch latch = new CountDownLatch(6);

    private static class InitThread implements Runnable{

        @Override
        public void run() {
            System.out.println("Thread_"+Thread.currentThread().getId()
                    +" ready init work......");
            latch.countDown();
            for(int i =0;i<2;i++) {
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +" ........continue do its work");
            }
        }
    }

    private static class BusiThread implements Runnable{

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
            }
            for(int i =0;i<3;i++) {
                System.out.println("BusiThread_"+Thread.currentThread().getId()
                        +" do business-----");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SleepTools.ms(1);
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +" ready init work step 1st......");
                latch.countDown();
                System.out.println("begin step 2nd.......");
                SleepTools.ms(1);
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +" ready init work step 2nd......");
                latch.countDown();
            }
        }).start();
        new Thread(new BusiThread()).start();
        for(int i=0;i<=3;i++){
            Thread thread = new Thread(new InitThread());
            thread.start();
        }

        latch.await();
        System.out.println("Main do ites work........");
    }
}
