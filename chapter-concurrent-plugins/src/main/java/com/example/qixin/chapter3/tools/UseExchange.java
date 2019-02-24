package com.example.qixin.chapter3.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/** 演示CyclicExchange用法
 * 创  建   时  间： 2019/2/22 0:18
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseExchange {

    private static final Exchanger<Set<String>> exchange = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(() -> {
            Set<String> setA = new HashSet<>();//存放数据的容器
            try {
                /*添加数据
                 * set.add(.....)
                 * */
                setA = exchange.exchange(setA);//交换set
                /*处理交换后的数据*/
            } catch (InterruptedException e) {
            }
        }).start();

        new Thread(() -> {
            Set<String> setB = new HashSet<>();//存放数据的容器
            try {
                /*添加数据
                 * set.add(.....)
                 * set.add(.....)
                 * */
                setB = exchange.exchange(setB);//交换set
                /*处理交换后的数据*/
            } catch (InterruptedException e) {
            }
        }).start();

    }
}
