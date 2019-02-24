package com.example.qixin.chapter7;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/** 存放到延迟队列的元素，对业务数据进行了包装
 * 创  建   时  间： 2019/2/22 0:42
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class ItemVo<T> implements Delayed {

    private long activeTime;//到期时间,单位毫秒
    private T data;//业务数据，泛型

    public ItemVo(long activeTime, T data) {
        super();
        this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime,TimeUnit.MILLISECONDS) + System.nanoTime();;
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    /*
     * 这个方法返回到激活日期的剩余时间，时间单位由单位参数指定。
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime - System.nanoTime(),
                TimeUnit.NANOSECONDS);
        return d;
    }

    /*
     *Delayed接口继承了Comparable接口，按剩余时间排序
     */
    @Override
    public int compareTo(Delayed o) {
        long d = (getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }
}
