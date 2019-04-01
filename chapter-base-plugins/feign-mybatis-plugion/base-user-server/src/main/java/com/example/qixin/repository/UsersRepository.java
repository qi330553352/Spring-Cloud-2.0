package com.example.qixin.repository;

import com.example.qixin.entity.Users;
import org.springframework.data.repository.Repository;

/**
 * 创 建 时 间: 2019/4/1
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
public interface UsersRepository extends Repository<Users,Integer> {

    Users findByUserName(String userName);

    Users findByUserNameOrEmail(String username,String email);
}
