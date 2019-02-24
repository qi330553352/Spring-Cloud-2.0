package com.example.qixin.chapter1;

import java.util.concurrent.ExecutionException;

/** 守护线程的使用
 * 创  建   时  间： 2019/2/18 23:57
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class DaemonThread {

    private static class UseThread extends Thread{
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName() + " interrupt flag is " + isInterrupted());
            } finally {
                //守护线程中finally不一定起作用
                System.out.println(" .............finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseThread useThread = new UseThread();
        useThread.setDaemon(true); //设置为守护线程  （注意一定要在start之前）
        useThread.start();
        Thread.sleep(5);
        useThread.interrupt();
    }
}
