package com.example.qixin.service;

import com.example.qixin.entity.Users;
import com.example.qixin.feign.UsersApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创  建   时  间： 2019/1/28 23:03
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class UsersService {

    @Autowired
    private UsersApi usersApi;

    public Users findById(Long id) {

        return usersApi.findById(id);
    }

    public List<Users> findAll() {

        return usersApi.findAll();
    }

    public Integer insertByBook(Users book) {

        return usersApi.save(book);
    }

    public int update(Users book) {

        return usersApi.updateById(book.getId(),book);
    }

    public int delete(Long id) {

        return usersApi.deleteById(id);
    }
}
