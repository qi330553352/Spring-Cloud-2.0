package com.example.qixin.service;

import com.example.qixin.entity.Users;
import com.example.qixin.feign.UsersApi;
import com.example.qixin.mapper.UsersMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 创  建   时  间： 2019/1/28 22:16
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Service
public class UsersService implements UsersApi {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users findById(@PathVariable("id") Long id) {

        return usersMapper.findById(id);
    }
}
