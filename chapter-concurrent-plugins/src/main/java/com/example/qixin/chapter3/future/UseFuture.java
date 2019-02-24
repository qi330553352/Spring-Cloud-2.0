package com.example.qixin.chapter3.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/** 演示Future等的使用
 * 创  建   时  间： 2019/2/22 0:15
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseFuture {

    /*实现Callable接口，允许有返回值*/
    private static class UseCallable implements Callable<Integer> {
        private int sum;
        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算！");
            Thread.sleep(2000);
            for(int i=0 ;i<5000;i++){
                sum=sum+i;
            }
            System.out.println("Callable子线程计算结束！结果为: "+sum);
            return sum;
        }
    }

    public static void main(String[] args)throws InterruptedException, ExecutionException {
        UseCallable useCallable = new UseCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(useCallable); //用FutureTask包装Callable
        new Thread(futureTask).start();//交给Thread去运行
        Random r = new Random();
        Thread.sleep(1000);
        if(r.nextBoolean()) {//用随机的方式决定是获得结果还是终止任务
            System.out.println("Get UseCallable result = "+futureTask.get());
        }else {
            System.out.println("中断计算。  ");
            futureTask.cancel(true);
        }
    }
}
