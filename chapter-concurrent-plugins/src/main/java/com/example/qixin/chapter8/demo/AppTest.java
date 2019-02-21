package com.example.qixin.chapter8.demo;

import com.example.qixin.chapter8.PendingJobPool;
import com.example.qixin.chapter8.vo.TaskResult;
import com.example.qixin.tools.SleepTools;

import java.util.List;
import java.util.Random;

/** 模拟一个应用程序，提交工作和任务，并查询任务进度
 * 创  建   时  间： 2019/2/22 0:46
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class AppTest {

    private final static String JOB_NAME = "计算数值";
    private final static int JOB_LENGTH = 1000;

    //查询任务进度的线程
    private static class QueryResult implements Runnable{

        private PendingJobPool pool;

        public QueryResult(PendingJobPool pool) {
            super();
            this.pool = pool;
        }

        @Override
        public void run() {
            int i=0;
            while(i<350) {
                List<TaskResult<String>> taskDetail = pool.getTaskDetail(JOB_NAME);
                if(!taskDetail.isEmpty()) {
                    System.out.println(pool.getTaskProgess(JOB_NAME));
                    System.out.println(taskDetail);
                }
                SleepTools.ms(100);
                i++;
            }
        }

    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        PendingJobPool pool = PendingJobPool.buildPool();
        pool.registerJob(JOB_NAME, JOB_LENGTH, myTask,1000*5);
        Random r = new Random();
        for(int i=0;i<JOB_LENGTH;i++) {
            pool.putTask(JOB_NAME, r.nextInt(1000));
        }
        Thread t = new Thread(new QueryResult(pool));
        t.start();
    }
}
