package com.example.qixin.repository;

import com.example.qixin.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** Spring-JDBC
 * 创 建 时 间: 2019/4/1
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@Repository
public class UserRepositoryImpl{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Users user) {
        return jdbcTemplate.update("INSERT INTO users(name, password, age) values(?, ?, ?)",
                user.getName(), user.getAge(), user.getAge());
    }
}
