package com.example.qixin.chapter5.cas;

import java.util.concurrent.atomic.AtomicInteger;

/** 演示基本类型的原子操作类
 * 创  建   时  间： 2019/2/22 0:28
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseAtomicInt {

    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }
}
