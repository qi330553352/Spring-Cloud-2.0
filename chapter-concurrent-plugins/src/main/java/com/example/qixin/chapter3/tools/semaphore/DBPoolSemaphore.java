package com.example.qixin.chapter3.tools.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/** 演示Semaphore用法，一个数据库连接池的实现
 * 创  建   时  间： 2019/2/22 0:20
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class DBPoolSemaphore {

    private final static int POOL_SIZE = 10;
    private final Semaphore useful,useless;//两个指示器，分别表示池子还有可用连接和已用连接
    //存放数据库连接的容器
    private static LinkedList<Connection> pool = new LinkedList<Connection>();
    //初始化池
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }
    public DBPoolSemaphore() {
        this.useful = new Semaphore(10);
        this.useless = new Semaphore(0);
    }

    /*归还连接*/
    public void returnConnect(Connection connection) throws InterruptedException {
        if(connection!=null) {
            System.out.println("当前有"+useful.getQueueLength()+"个线程等待数据库连接!!"
                    +"可用连接数："+useful.availablePermits());
            useless.acquire();
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }

    /*从池子拿连接*/
    public Connection takeConnect() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        useless.release();
        return connection;
    }
}
