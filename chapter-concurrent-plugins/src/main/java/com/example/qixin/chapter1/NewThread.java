package com.example.qixin.chapter1;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/** 新启线程的方式
 * 创  建   时  间： 2019/2/18 0:58
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
public class NewThread {

    private static class UseThread extends Thread{
        @Override
        public void run() {
            log.info("I am extended Thread.");
        }
    }

    private static class UseRunnable implements Runnable{
        @Override
        public void run() {
            log.info("I am implements Runnable.");
        }
    }

    private static class UseCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("I am implements Callable.");
            return "CallResult";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseThread useThread = new UseThread();
        useThread.start();

        UseRunnable useRunnable = new UseRunnable();
        new Thread(useRunnable).start();

        UseCallable useCallable = new UseCallable();
        FutureTask<String> futureTask = new FutureTask<>(useCallable); //用FutureTask包装Callable
        new Thread(futureTask).start();//交给Thread去运行
        System.out.println("Get UseCallable result = "+futureTask.get());
    }

}
