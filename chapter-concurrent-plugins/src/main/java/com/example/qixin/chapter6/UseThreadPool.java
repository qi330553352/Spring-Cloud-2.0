package com.example.qixin.chapter6;

import com.example.qixin.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 线程池的使用范例
 * 创  建   时  间： 2019/2/22 0:40
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseThreadPool {

    static class Worker implements Runnable
    {
        private String taskName;
        private Random r = new Random();

        public Worker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()
                    +" process the task : " + taskName);
            SleepTools.ms(r.nextInt(100)*5);
        }
    }

    public static void main(String[] args)
    {
        ExecutorService threadPool = new ThreadPoolExecutor(2, 4, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //ExecutorService cachePool = Executors.newCachedThreadPool();
        //ExecutorService fixPool = Executors.newFixedThreadPool(2);
        //ExecutorService singlePool = Executors.newSingleThreadExecutor();
        //ExecutorService forkjoinPool = Executors.newWorkStealingPool();
        for (int i = 0; i <= 6; i++)
        {
            Worker worker = new Worker("worker " + i);
            System.out.println("A new task has been added : " + worker.getName());
            threadPool.execute(worker);
        }
        threadPool.shutdown();
    }
}
