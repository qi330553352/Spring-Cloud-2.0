package com.example.qixin.chapter5.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 快递实体类-使用了Lock和Condition实现等待通知
 * 创  建   时  间： 2019/2/22 0:31
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class ExpressCond {

    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/
    private Lock kmLock     = new ReentrantLock();
    private Lock siteLock     = new ReentrantLock();
    private Condition kmCond = kmLock.newCondition();
    private Condition siteCond = siteLock.newCondition();

    public ExpressCond() {
    }

    public ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public void changeKm(){
        kmLock.lock();
        try {
            this.km = 101;
            kmCond.signal();
        } finally {
            kmLock.unlock();
        }
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  synchronized  void changeSite(){
        siteLock.lock();
        try {
            this.site = "BeiJing";
            siteCond.signal();
        } finally {
            siteLock.unlock();
        }
    }

    public void waitKm(){
        kmLock.lock();
        try {
            while(this.km<=100){//公里数小于100不做处理
                try {
                    kmCond.await();
                    System.out.println("Check Km thread["+Thread.currentThread().getId()+"] is be signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            kmLock.unlock();
        }
        System.out.println("the Km is "+this.km+",I will change db");
    }

    public void waitSite(){
        siteLock.lock();
        try {
            while(this.site.equals(CITY)){//快递到达目的地
                try {
                    siteCond.await();
                    System.out.println("Check Site thread["+Thread.currentThread().getId()+"] is be signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            siteLock.unlock();
        }
        System.out.println("the site is "+this.site+",I will call user");
    }
}
