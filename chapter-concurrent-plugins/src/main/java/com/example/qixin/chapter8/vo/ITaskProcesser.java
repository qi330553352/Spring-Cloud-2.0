package com.example.qixin.chapter8.vo;

/** 类说明：要求框架使用者实现的任务接口，因为任务的性质在调用时才知道，
 *所以传入的参数和方法的返回值均使用泛型
 * 创  建   时  间： 2019/2/22 0:46
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public interface ITaskProcesser<T, R> {

    TaskResult<R> taskExecute(T data);

}
