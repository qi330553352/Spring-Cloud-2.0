package com.example.qixin.chapter1.safeend;

/** 阻塞方法中抛出InterruptedException异常后，如果需要继续中断，需要手动再中断一次
 * 创  建   时  间： 2019/2/18 23:34
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class HasInterrputException {

    private static class UseThread extends Thread{

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while(!isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() +" in InterruptedException interrupt flag is " +isInterrupted());
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " I am extends Thread.");
            }
            System.out.println(Thread.currentThread().getName() +" interrupt flag is "+isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("HasInterrputEx");
        endThread.start();
        Thread.sleep(500);
        endThread.interrupt();
    }

}
