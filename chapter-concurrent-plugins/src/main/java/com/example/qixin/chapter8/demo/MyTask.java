package com.example.qixin.chapter8.demo;

import com.example.qixin.chapter8.vo.ITaskProcesser;
import com.example.qixin.chapter8.vo.TaskResult;
import com.example.qixin.chapter8.vo.TaskResultType;
import com.example.qixin.tools.SleepTools;

import java.util.Random;

/** 一个实际任务类，将数值加上一个随机数，并休眠随机时间
 * 创  建   时  间： 2019/2/22 0:44
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class MyTask implements ITaskProcesser<Integer,Integer> {

    @Override
    public TaskResult<Integer> taskExecute(Integer data) {
        Random r = new Random();
        int flag = r.nextInt(500);
        SleepTools.ms(flag);
        if(flag<=300) {//正常处理的情况
            Integer returnValue = data.intValue()+flag;
            return new TaskResult<Integer>(TaskResultType.Success,returnValue);
        }else if(flag>301&&flag<=400) {//处理失败的情况
            return new TaskResult<Integer>(TaskResultType.Failure,-1,"Failure");
        }else {//发生异常的情况
            try {
                throw new RuntimeException("异常发生了！！");
            } catch (Exception e) {
                return new TaskResult<Integer>(TaskResultType.Exception,
                        -1,e.getMessage());
            }
        }
    }
}
