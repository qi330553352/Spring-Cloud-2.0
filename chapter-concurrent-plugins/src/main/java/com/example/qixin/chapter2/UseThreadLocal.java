package com.example.qixin.chapter2;

/** 演示ThreadLocal的用法
 * 创  建   时  间： 2019/2/19 22:21
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseThreadLocal {

    /*声明一个ThreadLocal类型的static变量，所有线程共享,并初始化为1*/
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    /* 运行3个线程 */
    public void StartThreadArray(){
        Thread[] runs = new Thread[3];
        for(int i=0;i<runs.length;i++){
            runs[i]=new Thread(new TestThread(i));
        }
        for(int i=0;i<runs.length;i++){
            runs[i].start();
        }
    }

    /* 测试线程，线程的工作是将ThreadLocal变量的值变化，并写回，看看线程之间是否会互相影响 */
    public static class TestThread implements Runnable{
        int id;
        public TestThread(int id){
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":start");
            Integer s = threadLocal.get();//获得变量的值
            s = s+id;
            threadLocal.set(s);//将值写回变量
            System.out.println(Thread.currentThread().getName()+':'+threadLocal.get());
        }
    }

    public static void main(String[] args){
        UseThreadLocal test = new UseThreadLocal();
        test.StartThreadArray();
    }
}
