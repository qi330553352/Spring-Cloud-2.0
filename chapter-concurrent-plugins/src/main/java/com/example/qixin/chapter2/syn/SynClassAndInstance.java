package com.example.qixin.chapter2.syn;

import com.example.qixin.tools.SleepTools;

/** 演示实例锁和类锁是不同的，两者可以并行,锁的实例不一样，也是可以并行的
 * 创  建   时  间： 2019/2/20 0:52
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class SynClassAndInstance {

    private static class SynClass extends Thread{
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            synClass();
        }
    }

    private static class InstanceSyn implements Runnable{
        private SynClassAndInstance SynClassAndInstance;

        public InstanceSyn(SynClassAndInstance SynClassAndInstance) {
            this.SynClassAndInstance = SynClassAndInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..."+SynClassAndInstance);
            SynClassAndInstance.instance();
        }
    }

    private static class Instance2Syn implements Runnable{
        private SynClassAndInstance SynClassAndInstance;

        public Instance2Syn(SynClassAndInstance SynClassAndInstance) {
            this.SynClassAndInstance = SynClassAndInstance;
        }
        @Override
        public void run() {
            System.out.println("TestInstance2 is running..."+SynClassAndInstance);
            SynClassAndInstance.instance2();
        }
    }


    private synchronized void instance(){
        SleepTools.second(3);
        System.out.println("synInstance is going..."+this.toString());
        SleepTools.second(3);
        System.out.println("synInstance ended "+this.toString());
    }

    private synchronized void instance2(){
        SleepTools.second(3);
        System.out.println("synInstance2 is going..."+this.toString());
        SleepTools.second(3);
        System.out.println("synInstance2 ended "+this.toString());
    }

    private static synchronized void synClass(){
        SleepTools.second(1);
        System.out.println("synClass going...");
        SleepTools.second(1);
        System.out.println("synClass end");
    }

    public static void main(String[] args) {
        SynClassAndInstance synClassAndInstance = new SynClassAndInstance();
        Thread t1 = new SynClass();
        Thread t2 = new Thread(new InstanceSyn(synClassAndInstance));
        Thread t3 = new Thread(new Instance2Syn(synClassAndInstance));
        SynClassAndInstance synClassAndInstance2 = new SynClassAndInstance();
        Thread t4 = new Thread(new InstanceSyn(synClassAndInstance2));
        t2.start();
        t3.start();
        t4.start();
        SleepTools.second(1);
        t1.start();
    }

}
