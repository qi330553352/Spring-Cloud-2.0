package com.example.qixin.mapper;

import com.example.qixin.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创  建   时  间： 2019/1/28 22:23
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public interface UsersMapper {


    Users findById(Long id);

    int deleteById(Long id);

    int save(Users bean);

    List<Users> findAll();

    List<Users> findUsers(@Param("start") Integer start, @Param("pageSize")Integer pageSize);
}
