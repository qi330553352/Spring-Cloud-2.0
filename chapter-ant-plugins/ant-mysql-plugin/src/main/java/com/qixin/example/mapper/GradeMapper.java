package com.qixin.example.mapper;

import com.qixin.example.entity.Grade;
import com.qixin.example.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创  建   时  间： 2019/3/5 22:06
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Repository
public interface GradeMapper {

    Grade findById(@Param("id")Long id);
    List<User> findUsersByGradeId(@Param("id")Long id);
}
