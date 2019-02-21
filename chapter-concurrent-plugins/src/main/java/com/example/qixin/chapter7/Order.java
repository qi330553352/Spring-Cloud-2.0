package com.example.qixin.chapter7;

/** 订单的实体类
 * 创  建   时  间： 2019/2/22 0:41
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Order {

    private final String orderNo;
    private final double orderMoney;

    public Order(String orderNo, double orderMoney) {
        super();
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

}
