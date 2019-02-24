package com.example.qixin.chapter1;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ExecutionException;

/** 停止线程
 * 创  建   时  间： 2019/2/18 23:06
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
public class StopThread {

    private static class UseThread extends Thread{
        @Override
        public void run() {
            log.info("I am extended Thread.");
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseThread useThread = new UseThread();
        useThread.start();
        useThread.stop();//强制终结线程，无法保证这线程资源被正常释放；
    }


}
