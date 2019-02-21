package com.example.qixin.chapter5.cas;

import java.util.concurrent.atomic.AtomicReference;

/** 演示引用类型的原子操作类
 * 创  建   时  间： 2019/2/22 0:29
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UseAtomicReference {
    public static AtomicReference<UserInfo> atomicUserRef = new
            AtomicReference<UserInfo>();//原子引用类型的实例
    public static void main(String[] args) {
        UserInfo user = new UserInfo("Mark", 15);//要修改的实体的实例
        atomicUserRef.set(user);//用原子引用类型包装
        UserInfo updateUser = new UserInfo("Bill", 17);//要变化成为的新实例
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println(atomicUserRef.get().getName());
        System.out.println(atomicUserRef.get().getAge());
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    //定义一个实体类
    static class UserInfo {
        private String name;
        private int age;
        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }
}
