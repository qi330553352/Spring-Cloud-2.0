package com.example.qixin.chapter4.tranfer;

import com.example.qixin.chapter4.tranfer.service.ITransfer;
import com.example.qixin.chapter4.tranfer.service.SafeOperateToo;

/** 模拟支付公司转账的动作
 * 创  建   时  间： 2019/2/22 0:23
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class PayCompany {

    /*执行转账动作的线程*/
    private static class TransferThread extends Thread{

        private String name;
        private UserAccount from;
        private UserAccount to;
        private int amount;
        private ITransfer transfer;

        public TransferThread(String name, UserAccount from, UserAccount to,
                              int amount, ITransfer transfer) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.transfer = transfer;
        }


        public void run(){
            Thread.currentThread().setName(name);
            try {
                transfer.transfer(from,to,amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        PayCompany payCompany = new PayCompany();
        UserAccount zhangsan = new UserAccount("zhangsan",20000);
        UserAccount lisi = new UserAccount("lisi",20000);
        ITransfer transfer = new SafeOperateToo();
        TransferThread zhangsanToLisi = new TransferThread("zhangsanToLisi"
                ,zhangsan,lisi,2000,transfer);
        TransferThread lisiToZhangsan = new TransferThread("lisiToZhangsan"
                ,lisi,zhangsan,4000,transfer);
        zhangsanToLisi.start();
        lisiToZhangsan.start();

    }

}
