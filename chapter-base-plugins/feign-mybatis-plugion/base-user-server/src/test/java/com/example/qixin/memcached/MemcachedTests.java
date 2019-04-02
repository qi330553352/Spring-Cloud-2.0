package com.example.qixin.memcached;

import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 创 建 时 间: 2019/4/2
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemcachedTests {

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void testGetSet() throws Exception {
        memcachedClient.set("hello", 0, "Hello,xmemcached");
        String value = memcachedClient.get("hello");
        System.out.println("hello=" + value);
        memcachedClient.delete("hello");
    }

    @Test
    public void testMore() throws Exception {
        if (!memcachedClient.set("hello", 0, "world")) {
            System.err.println("set error");
        }
        if (!memcachedClient.add("hello", 0, "dennis")) {
            System.err.println("Add error,key is existed");
        }
        if (!memcachedClient.replace("hello", 0, "dennis")) {
            System.err.println("replace error");
        }
        memcachedClient.append("hello", " good");
        memcachedClient.prepend("hello", "hello ");
        String name = memcachedClient.get("hello", new StringTranscoder());
        System.out.println(name);
        memcachedClient.deleteWithNoReply("hello");
    }

    @Test
    public void testIncrDecr() throws Exception {
        memcachedClient.delete("Incr");
        memcachedClient.delete("Decr");
        System.out.println(memcachedClient.incr("Incr", 6, 12));
        System.out.println(memcachedClient.incr("Incr", 3));
        System.out.println(memcachedClient.incr("Incr", 2));
        System.out.println(memcachedClient.decr("Decr", 1, 6));
        System.out.println(memcachedClient.decr("Decr", 2));
    }

    @Test
    public void testCounter() throws Exception {
//        MemcachedClient memcachedClient = memcachedUtil.getMemcachedClient();
        Counter counter=memcachedClient.getCounter("counter",10);
        System.out.println("counter="+counter.get());
        long c1 =counter.incrementAndGet();
        System.out.println("counter="+c1);
        long c2 =counter.decrementAndGet();
        System.out.println("counter="+c2);
        long c3 =counter.addAndGet(-10);
        System.out.println("counter="+c3);
    }

    @Test
    public void testTouch() throws Exception {
        memcachedClient.set("Touch", 2, "Touch Value");
        Thread.sleep(1000);
        memcachedClient.touch("Touch",6);
        Thread.sleep(2000);
        String value =memcachedClient.get("Touch",3000);
        System.out.println("Touch=" + value);
    }
}
