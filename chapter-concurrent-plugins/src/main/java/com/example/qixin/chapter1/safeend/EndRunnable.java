package com.example.qixin.chapter1.safeend;

/** 实现接口Runnable的线程如何中断
 * 创  建   时  间： 2019/2/18 23:29
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class EndRunnable {

    private static class UseRunnable implements Runnable{
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {//改为true，线程不会中断;改为Thread.interrupted()，标志位被复位
                System.out.println(Thread.currentThread().getName()+ " I am implements Runnable.");
            }
            System.out.println(Thread.currentThread().getName()+" interrupt flag is "+Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable,"endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
