package com.example.qixin.chapter4;

/** 演示普通账户的死锁和解决
 * 创  建   时  间： 2019/2/22 0:23
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class NormalDeadLock {

    private static Object valueFirst = new Object();//第一个锁
    private static Object valueSecond = new Object();//第二个锁

    //先拿第一个锁，再拿第二个锁
    private static void fisrtToSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (valueFirst){
            System.out.println(threadName+" get valueSecond");
            Thread.sleep(100);
            synchronized (valueSecond){
                System.out.println(threadName+" get valueFirst");
            }
        }
    }

    //先拿第二个锁，再拿第一个锁
    private static void SecondToFisrt() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (valueSecond){
            System.out.println(threadName+" get valueSecond");
            Thread.sleep(100);
            synchronized (valueFirst){
                System.out.println(threadName+" get valueFirst");
            }
        }
    }

    private static class TestThread extends Thread{

        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        public void run(){
            Thread.currentThread().setName(name);
            try {
                SecondToFisrt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("TestDeadLock");
        TestThread testThread = new TestThread("SubTestThread");
        testThread.start();
        try {
            fisrtToSecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
