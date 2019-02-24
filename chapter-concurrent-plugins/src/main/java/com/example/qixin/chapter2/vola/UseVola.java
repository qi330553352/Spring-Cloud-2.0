package com.example.qixin.chapter2.vola;

import com.example.qixin.tools.SleepTools;

/** 演示volatile的使用
 * 创  建   时  间： 2019/2/20 0:57
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseVola {

    private volatile int age = 100000;

    private static class TestThread extends Thread{

        private UseVola synTest;

        public TestThread(UseVola synTest,String name) {
            super(name);
            this.synTest = synTest;
        }

        @Override
        public void run() {
            for(int i=0;i<100000;i++) {
                synTest.test();
            }
            System.out.println(Thread.currentThread().getName() +" age =  "+synTest.getAge());
        }
    }

    public void test() {
        age++;
    }

    public int getAge() {
        return age;
    }


    public static void main(String[] args) throws InterruptedException {
        UseVola synTest = new UseVola();
        Thread endThread = new TestThread(synTest,"endThread");
        endThread.start();
        SleepTools.ms(2);
        System.out.println(Thread.currentThread().getName() +" age =  "+synTest.getAge());

    }

}
