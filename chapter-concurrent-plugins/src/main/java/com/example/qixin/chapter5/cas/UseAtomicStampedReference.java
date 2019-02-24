package com.example.qixin.chapter5.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/** 演示带版本戳的原子操作类
 * 创  建   时  间： 2019/2/22 0:29
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseAtomicStampedReference {

    static AtomicStampedReference<String> asr = new AtomicStampedReference("mark", 0);

    public static void main(String[] args) throws InterruptedException {
        final int oldStamp = asr.getStamp();//拿初始版本0
        final String oldReference = asr.getReference();//初始值
        System.out.println(oldReference+"============"+oldStamp);
        Thread rightStampThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+":当前变量值："
                    +oldReference + "-当前版本戳：" + oldStamp + "-"
                    + asr.compareAndSet(oldReference,
                    oldReference + "+Java", oldStamp, oldStamp + 1));
        });

        Thread errorStampThread = new Thread(() -> {
            String reference = asr.getReference();//变量的最新值
            System.out.println(Thread.currentThread().getName()+":当前变量值："
                    +reference + "-当前版本戳：" + asr.getStamp() + "-"
                    + asr.compareAndSet(reference,
                    reference + "+C", oldStamp, oldStamp + 1));
        });
        rightStampThread.start();
        rightStampThread.join();
        errorStampThread.start();
        errorStampThread.join();

        System.out.println(asr.getReference()+"============"+asr.getStamp());
    }
}
