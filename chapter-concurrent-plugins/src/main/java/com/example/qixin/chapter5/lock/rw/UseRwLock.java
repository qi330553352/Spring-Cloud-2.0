package com.example.qixin.chapter5.lock.rw;

import com.example.qixin.tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 用读写锁来实现商品服务接口
 * 创  建   时  间： 2019/2/22 0:34
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseRwLock implements GoodsService {
    private GoodsInfo goodsInfo;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock getLock = lock.readLock();//get
    private final Lock setLock = lock.writeLock();//set

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        SleepTools.ms(5);
        try {
            return this.goodsInfo;
        } finally {
            getLock.unlock();
        }
    }

    @Override
    public void setNum(int number) {
        setLock.lock();
        try {
            SleepTools.ms(50);
            this.goodsInfo.changeNumber(number);
        } finally {
            setLock.unlock();
        }

    }
}
