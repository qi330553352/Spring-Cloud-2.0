package com.example.qixin.chapter3.forkjoin.dirs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/** 遍历指定目录（含子目录）统计文件个数
 * 创  建   时  间： 2019/2/22 0:16
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class SumDirsFiles extends RecursiveTask<Integer> {

    private File path;

    public SumDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected Integer compute() {
        int count = 0;
        int dirCount = 0;
        List<SumDirsFiles> subTasks = new ArrayList<>();

        File[] files = path.listFiles();
        if (files!=null){
            for (File file : files) {
                if (file.isDirectory()) {
                    // 对每个子目录都新建一个子任务。
                    subTasks.add(new SumDirsFiles(file));
                    dirCount++;//统计目录个数
                } else {
                    count++;// 遇到文件，文件个数加1。
                }
            }
            System.out.println("目录:" + path.getAbsolutePath()
                    +"包含目录个数："+dirCount+",文件个数："+count);
            if (!subTasks.isEmpty()) {
                // 在当前的 ForkJoinPool 上调度所有的子任务。
                for (SumDirsFiles subTask : invokeAll(subTasks)) {
                    count = count+subTask.join();
                }
            }
        }
        return count;
    }

    public static void main(String [] args){
        try {
            // 用一个 ForkJoinPool 实例调度“总任务”
            ForkJoinPool pool = new ForkJoinPool();
            //new一个ForkJoinTask的实例
            SumDirsFiles task = new SumDirsFiles(new File("F:/"));

            pool.invoke(task);//提交给ForkJoinPool执行Task
            System.out.println("Task is Running......");
            System.out.println("File counts ="+task.join());

            System.out.println("Task end");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
