package com.example.qixin.chapter2.wn;

/** 快递实体类
 * 创  建   时  间： 2019/2/22 0:11
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Express {

    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm(){
        this.km = 101;
        notifyAll();
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  synchronized  void changeSite(){
        this.site = "BeiJing";
        notifyAll();
    }

    public synchronized void waitKm(){
        while(this.km<=100){//公里数小于100不做处理
            try {
                wait();
                System.out.println("Check Km thread["+Thread.currentThread().getId()
                        +"] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the Km is "+this.km+",I will change db");
    }

    public synchronized void waitSite(){
        while(this.site.equals(CITY)){//快递到达目的地
            try {
                wait();
                System.out.println("Check Site thread["+Thread.currentThread().getId()
                        +"] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the site is "+this.site+",I will call user");
    }

}
