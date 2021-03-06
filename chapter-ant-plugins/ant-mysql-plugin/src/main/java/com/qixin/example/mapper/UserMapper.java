package com.qixin.example.mapper;

import com.qixin.example.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创  建   时  间： 2019/3/2 20:10
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Repository
public interface UserMapper {

    List<User> findUsers();

    //一对一映射
    User findById(@Param("id") Long id); //方法一: 使用点符号和嵌套对象
    User findUserWithAddressById(@Param("id") Long id);//方法二: 使用嵌套ResultMap
}
