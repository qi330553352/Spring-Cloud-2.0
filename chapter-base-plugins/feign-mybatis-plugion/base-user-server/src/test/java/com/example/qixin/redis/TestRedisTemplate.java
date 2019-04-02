package com.example.qixin.redis;

import com.example.qixin.entity.Users;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 创 建 时 间: 2019/4/2
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString()  {
        redisTemplate.opsForValue().set("neo", "ityouknow");
        Assert.assertEquals("ityouknow", redisTemplate.opsForValue().get("neo"));
    }

    @Test
    public void testObj(){
        Users user=new Users("小王",31,new Date());
        ValueOperations<String, Users> operations=redisTemplate.opsForValue();
        operations.set("com.neo", user);
        Users u=operations.get("com.neo");
        System.out.println("user: "+u.toString());
    }

    @Test
    public void testExpire() throws InterruptedException {
        Users user=new Users("小王",31,new Date());
        ValueOperations<String, Users> operations=redisTemplate.opsForValue();
        operations.set("expire", user,100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("expire");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }

    @Test
    public void testDelete() {
        ValueOperations<String, Users> operations=redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("deletekey", "ityouknow");
        redisTemplate.delete("deletekey");
        boolean exists=redisTemplate.hasKey("deletekey");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash","you","you");
        String value=(String) hash.get("hash","you");
        System.out.println("hash value :"+value);
    }

    @Test
    public void testList() {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.leftPush("list","it");
        list.leftPush("list","you");
        list.leftPush("list","know");
        String value=(String)list.leftPop("list");
        System.out.println("list value :"+value.toString());
    }

    @Test
    public void testSet() {
        String key="set";
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key,"it");
        set.add(key,"you");
        set.add(key,"you");
        set.add(key,"know");
        Set<String> values=set.members(key);
        for (String v:values){
            System.out.println("set value :"+v);
        }
    }

    @Test //difference() 函数会把 key 1 中不同于 key 2 的数据对比出来，这个特性适合我们在金融场景中对账的时候使用
    public void testDiffrence(){
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key1="setMore1";
        String key2="setMore2";
        set.add(key1,"it");
        set.add(key1,"you");
        set.add(key1,"you");
        set.add(key1,"know");
        set.add(key2,"xx");
        set.add(key2,"know");
        Set<String> diffs=set.difference(key1,key2);
        for (String v:diffs){
            System.out.println("diffs set value :"+v);
        }
    }

    @Test //unions 会取两个集合的合集，Set 还有其他很多类似的操作，非常方便我们对集合进行数据处理
    public void testUnions(){
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key3="setMore3";
        String key4="setMore4";
        set.add(key3,"it");
        set.add(key3,"you");
        set.add(key3,"xx");
        set.add(key4,"aa");
        set.add(key4,"bb");
        set.add(key4,"know");
        Set<String> unions=set.union(key3,key4);
        for (String v:unions){
            System.out.println("unions value :"+v);
        }
    }

    @Test
    public void testZset(){
        String key="zset";
        redisTemplate.delete(key);
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        zset.add(key,"it",1);
        zset.add(key,"you",6);
        zset.add(key,"know",4);
        zset.add(key,"neo",3);

        Set<String> zsets=zset.range(key,0,3);
        for (String v:zsets){
            System.out.println("zset value :"+v);
        }

        Set<String> zsetB=zset.rangeByScore(key,0,3);
        for (String v:zsetB){
            System.out.println("zsetB value :"+v);
        }
    }
}
