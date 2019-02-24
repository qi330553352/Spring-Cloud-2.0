package com.example.qixin.chapter4.tranfer.service;

import com.example.qixin.chapter4.tranfer.UserAccount;

import java.util.Random;

/** 不会产生死锁的安全转账第二种方法
 * 创  建   时  间： 2019/2/22 0:26
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class SafeOperateToo implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)throws InterruptedException {
        Random r = new Random();
        while(true){
            if(from.getLock().tryLock()){
                System.out.println(Thread.currentThread().getName()+" get "+from.getName());
                //Thread.sleep(100);
                try{
                    if(to.getLock().tryLock()){
                        try{
                            System.out.println(Thread.currentThread().getName()+" get "+to.getName());
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            System.out.println(from);
                            System.out.println(to);
                            break;
                        }finally {
                            to.getLock().unlock();
                        }
                    }
                }finally {
                    from.getLock().unlock();
                }
            }
            //Thread.sleep(r.nextInt(10));
        }

    }
}
