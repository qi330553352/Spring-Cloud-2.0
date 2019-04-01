package com.example.qixin.repository;

import com.example.qixin.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 创 建 时 间: 2019/4/1
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByName(String name);

    Users findByNameAndCreateTime(String name,Date createTime);
}
