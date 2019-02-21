package com.example.qixin.chapter4.tranfer.service;

import com.example.qixin.chapter4.tranfer.UserAccount;

/** 银行转账动作接口
 * 创  建   时  间： 2019/2/22 0:25
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public interface ITransfer {

    void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException;

}
