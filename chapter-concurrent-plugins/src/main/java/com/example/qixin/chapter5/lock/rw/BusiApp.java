package com.example.qixin.chapter5.lock.rw;

import com.example.qixin.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/** 对商品进行业务的应用
 * 创  建   时  间： 2019/2/22 0:33
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class BusiApp {

    static final int readWriteRatio = 10;//读写线程的比例
    static final int minthreadCount = 3;//最少线程数
    static CountDownLatch latch= new CountDownLatch(1);

    private static class GetThread implements Runnable{

        private GoodsService goodsService;
        public GetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            try {
                latch.await();//让读写线程同时运行
            } catch (InterruptedException e) {
            }
            long start = System.currentTimeMillis();
            for(int i=0;i<100;i++){//操作100次
                goodsService.getNum();
            }
            System.out.println(Thread.currentThread().getName()+"读取商品数据耗时："
                    +(System.currentTimeMillis()-start)+"ms");

        }
    }

    private static class SetThread implements Runnable{

        private GoodsService goodsService;
        public SetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            try {
                latch.await();//让读写线程同时运行
            } catch (InterruptedException e) {
            }
            long start = System.currentTimeMillis();
            Random r = new Random();
            for(int i=0;i<10;i++){//操作10次
                SleepTools.ms(50);
                goodsService.setNum(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName()
                    +"写商品数据耗时："+(System.currentTimeMillis()-start)+"ms---------");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        GoodsInfo goodsInfo = new GoodsInfo("Cup",100000,10000);
        GoodsService goodsService = /*new UseRwLock(goodsInfo);*/new UseSyn(goodsInfo);
        for(int i = 0;i<minthreadCount;i++){
            Thread setT = new Thread(new SetThread(goodsService));
            for(int j=0;j<readWriteRatio;j++) {
                Thread getT = new Thread(new GetThread(goodsService));
                getT.start();
            }
            setT.start();
        }
        latch.countDown();

    }

}
