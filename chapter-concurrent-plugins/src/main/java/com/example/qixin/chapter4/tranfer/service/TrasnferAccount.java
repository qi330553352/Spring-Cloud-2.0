package com.example.qixin.chapter4.tranfer.service;

import com.example.qixin.chapter4.tranfer.UserAccount;

/** 不安全的转账动作的实现
 * 创  建   时  间： 2019/2/22 0:26
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class TrasnferAccount implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException {
        synchronized (from){
            System.out.println(Thread.currentThread().getName()
                    +" get"+from.getName());
            Thread.sleep(100);
            synchronized (to){
                System.out.println(Thread.currentThread().getName()
                        +" get"+to.getName());
                from.flyMoney(amount);
                to.addMoney(amount);
            }
        }
    }
}
