package com.qixin.example.service;

import com.qixin.example.entity.Grade;
import com.qixin.example.entity.User;
import com.qixin.example.mapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创  建   时  间： 2019/3/5 22:33
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    public Grade findById(long id){
        List<User> userList = gradeMapper.findUsersByGradeId(1L);
        userList.stream().forEach(System.out::println);
        return gradeMapper.findById(id);
    }
}
