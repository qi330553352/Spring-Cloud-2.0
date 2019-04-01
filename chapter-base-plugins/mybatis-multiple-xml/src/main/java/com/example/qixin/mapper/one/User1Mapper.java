package com.example.qixin.mapper.one;

import com.example.qixin.model.User;

import java.util.List;

/**
 * 创 建 时 间: 2019/4/1
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
public interface User1Mapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}
