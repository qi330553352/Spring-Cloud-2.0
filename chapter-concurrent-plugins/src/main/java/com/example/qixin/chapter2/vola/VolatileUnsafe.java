package com.example.qixin.chapter2.vola;

import com.example.qixin.tools.SleepTools;

/** 演示violate无法提供操作的原子性
 * 创  建   时  间： 2019/2/22 0:10
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class VolatileUnsafe {

    private static class VolatileVar implements Runnable {

        private volatile int a = 0;

        @Override
        public void run() {
            a = a + 1;
            System.out.println(Thread.currentThread().getName() + ":----" + a);
            SleepTools.ms(100);
            a = a + 1;
            System.out.println(Thread.currentThread().getName() + ":----" + a);
        }
    }

    public static void main(String[] args) {

        VolatileVar v = new VolatileVar();

        Thread t1 = new Thread(v);
        Thread t2 = new Thread(v);
        Thread t3 = new Thread(v);
        Thread t4 = new Thread(v);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
