package com.example.qixin.chapter1.safeend;

/** 如何安全中断线程
 * 创  建   时  间： 2019/2/18 23:25
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class EndThread {

    private static class UseThread extends Thread{
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while(!isInterrupted()) {//改为true，线程不会中断;改为Thread.interrupted()，标志位被复位
                System.out.println(Thread.currentThread().getName()+ " I am extends Thread.");
            }
            System.out.println(Thread.currentThread().getName()+" interrupt flag is "+isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }



}
