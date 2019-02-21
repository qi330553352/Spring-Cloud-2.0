package com.example.qixin.chapter2.syn;

/** 演示synchronized关键字的作用，不加和加上的区别，最后打印的线程应该打印100000
 * 创  建   时  间： 2019/2/20 0:56
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class SynTest {

    private int age = 100000;//初始100000

    private static class TestThread extends Thread{

        private SynTest synTest;

        public TestThread(SynTest synTest,String name) {
            super(name);
            this.synTest = synTest;
        }

        @Override
        public void run() {
            for(int i=0;i<100000;i++) {//递增100000
                synTest.test();
            }
            System.out.println(Thread.currentThread().getName()+" age =  "+synTest.getAge());
        }
    }

    public synchronized void test() {
        age++;
    }

    public void test2() {
        synchronized(this){
            age--;
        }
    }

    public int getAge() {
        return age;
    }


    public static void main(String[] args) throws InterruptedException {
        SynTest synTest = new SynTest();
        Thread endThread = new TestThread(synTest,"endThread");
        endThread.start();
        for(int i=0;i<100000;i++) {//递减100000
            synTest.test2();
        }
        System.out.println(Thread.currentThread().getName()+" age =  "+synTest.getAge());

    }

}
