package com.example.qixin.chapter8.vo;

import com.example.qixin.chapter8.CheckJobProcesser;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/** 提交给框架执行的工作实体类,工作：表示本批次需要处理的同性质任务(Task)的一个集合
 * 创  建   时  间： 2019/2/22 0:47
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class JobInfo<R> {
    //工作名，用以区分框架中唯一的工作，
    private final String jobName;
    private final int jobLength;//工作中任务的列表
    private final ITaskProcesser<?, ?> taskProcesser;//处理工作中任务的处理器
    private AtomicInteger successCount;//任务的成功次数
    private AtomicInteger taskProcessCount;//工作中任务目前已经处理的次数
    //存放每个任务的处理结果，供查询用,why?
    private LinkedBlockingDeque<TaskResult<R>> taskDetailQueus;
    private final long expireTime;//保留的工作的结果信息供查询的时间

    public JobInfo(String jobName,int jobLength,ITaskProcesser<?, ?> taskProcesser,long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        successCount = new AtomicInteger(0);
        taskProcessCount = new AtomicInteger(0);
        this.taskProcesser = taskProcesser;
        taskDetailQueus = new LinkedBlockingDeque<TaskResult<R>>(jobLength);
        this.expireTime = expireTime;
    }

    public int getSuccCount() {
        return successCount.get();
    }

    public int getTaskProcessCount() {
        return taskProcessCount.get();
    }

    //提供工作中失败的次数
    public int getFailCount() {
        return taskProcessCount.get() - successCount.get();
    }

    public ITaskProcesser<?, ?> getTaskProcesser() {
        return taskProcesser;
    }

    //提供工作的整体进度信息
    public String getTotalProcess() {
        return "Success["+successCount.get()+"]/Current["+taskProcessCount.get()
                +"] Total["+jobLength+"]";
    }

    public int getJobLength() {
        return jobLength;
    }

    //提供工作中每个任务的处理结果
    public List<TaskResult<R>> getTaskDetail(){
        List<TaskResult<R>> taskDetailList = new LinkedList<>();
        TaskResult<R> taskResult;
        while((taskResult=taskDetailQueus.pollFirst())!=null) {
            taskDetailList.add(taskResult);
        }
        return taskDetailList;
    }

    //每个任务处理完成后，记录任务的处理结果，因为从业务应用的角度来说，对查询任务进度数据的一致性要不高
    //我们保证最终一致性即可，无需对整个方法加锁
    public void addTaskResult(TaskResult<R> result,CheckJobProcesser checkJob) {
        if(TaskResultType.Success.equals(result.getResultType())) {
            successCount.incrementAndGet();
        }else {
            taskDetailQueus.addLast(result);
        }
        taskProcessCount.incrementAndGet();
        //工作的所有任务都处理完成后，提供一定的时间供查询结果，
        //所以将工作推入定时缓存，到期将工作从框架中清除
        if(taskProcessCount.get()==jobLength) {
            checkJob.putJob(jobName, expireTime);
        }
    }
}
