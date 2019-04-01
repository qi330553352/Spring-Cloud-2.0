package com.example.qixin.service;

import com.example.qixin.entity.BaseResult;
import com.example.qixin.entity.Users;
import com.example.qixin.feign.UsersApi;
import com.example.qixin.mapper.UsersMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    @Override
    public List<Users> findAll() {

        return usersMapper.findAll();
    }

    @Override
    public Long save(@RequestBody Users users) {
        int result = usersMapper.save(users);
        return result>0?users.getId():0L;
    }

    @Override
    public int updateById(@PathVariable("id") Long id, @RequestBody Users users) {

        return usersMapper.updateById(id,users);
    }

    @Override
    public int deleteById(@PathVariable("id") Long id) {

        return usersMapper.deleteById(id);
    }
}
