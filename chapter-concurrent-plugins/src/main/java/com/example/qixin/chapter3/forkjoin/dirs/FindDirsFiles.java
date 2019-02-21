package com.example.qixin.chapter3.forkjoin.dirs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/** 遍历指定目录（含子目录）找寻指定类型文件
 * 创  建   时  间： 2019/2/22 0:15
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class FindDirsFiles extends RecursiveAction {

    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks = new ArrayList<>();

        File[] files = path.listFiles();
        if (files!=null){
            for (File file : files) {
                if (file.isDirectory()) {
                    // 对每个子目录都新建一个子任务。
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    // 遇到文件，检查。
                    if (file.getAbsolutePath().endsWith("txt")){
                        System.out.println("文件:" + file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()) {
                // 在当前的 ForkJoinPool 上调度所有的子任务。
                for (FindDirsFiles subTask : invokeAll(subTasks)) {
                    subTask.join();
                }
            }
        }
    }

    public static void main(String [] args){
        try {
            // 用一个 ForkJoinPool 实例调度总任务
            ForkJoinPool pool = new ForkJoinPool();
            FindDirsFiles task = new FindDirsFiles(new File("F:/"));

            pool.execute(task);

            System.out.println("Task is Running......");
            Thread.sleep(1);
            int otherWork = 0;
            for(int i=0;i<100;i++){
                otherWork = otherWork+i;
            }
            System.out.println("Main Thread done sth......,otherWork="+otherWork);
            task.join();
            System.out.println("Task end");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
