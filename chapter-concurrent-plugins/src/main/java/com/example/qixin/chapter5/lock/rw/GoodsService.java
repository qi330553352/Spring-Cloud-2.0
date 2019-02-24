package com.example.qixin.chapter5.lock.rw;

/** 商品相关服务的接口
 * 创  建   时  间： 2019/2/22 0:34
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public interface GoodsService {

    GoodsInfo getNum();
    void setNum(int number);

}
