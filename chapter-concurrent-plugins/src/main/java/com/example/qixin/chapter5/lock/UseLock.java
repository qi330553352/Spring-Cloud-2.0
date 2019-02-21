package com.example.qixin.chapter5.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 演示Lock的标准用法
 * 创  建   时  间： 2019/2/22 0:31
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            //do business......
        } finally {
            lock.unlock();
        }
    }
}
