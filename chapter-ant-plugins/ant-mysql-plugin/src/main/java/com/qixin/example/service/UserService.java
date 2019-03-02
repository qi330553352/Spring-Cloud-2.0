package com.qixin.example.service;

import com.qixin.example.entity.User;
import com.qixin.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创  建   时  间： 2019/3/2 19:39
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findUsers() {

        return userMapper.findUsers();
    }
}
