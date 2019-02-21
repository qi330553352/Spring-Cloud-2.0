package com.example.qixin.chapter5.lock.condition;

/** 测试Lock和Condition实现等待通知
 * 创  建   时  间： 2019/2/22 0:32
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class TestCond {

    private static ExpressCond express = new ExpressCond(0,ExpressCond.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<3;i++){
            new CheckSite().start();
        }
        for(int i=0;i<3;i++){
            new CheckKm().start();
        }

        Thread.sleep(1000);
        express.changeKm();//快递地点变化
    }

}
