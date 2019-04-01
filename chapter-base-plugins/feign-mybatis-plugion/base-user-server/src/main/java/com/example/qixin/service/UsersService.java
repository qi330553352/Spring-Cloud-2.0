package com.example.qixin.service;

import com.example.qixin.entity.Users;
import com.example.qixin.feign.UsersApi;
import com.example.qixin.mapper.UsersMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Users findById(@PathVariable("id") Long id) {
        //方法一：Spring Boot使用JDBC操作数据库
        Object[] objects = new Object[]{id};
        return jdbcTemplate.queryForObject("SELECT * FROM user_info WHERE id=?", objects, new BeanPropertyRowMapper<>(Users.class));

//        return usersMapper.findById(id);
    }

    @Override
    public List<Users> findAll() {
        //方法一：Spring Boot使用JDBC操作数据库
//        return jdbcTemplate.query("SELECT * FROM user_info", new BeanPropertyRowMapper<>(Users.class));
//        return jdbcTemplate.query("SELECT * FROM user_info", new UsersRowMapper());
        return usersMapper.findAll();
    }

    public class UsersRowMapper implements RowMapper<Users> {
        @Override
        public Users mapRow(ResultSet rs, int i) throws SQLException {
            Users user = new Users();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            user.setCreateTime(rs.getDate("createTime"));
            return user;
        }
    }

    @Override
    public int save(@RequestBody Users users) {
        //方法一：Spring Boot使用JDBC操作数据库
        return jdbcTemplate.update("INSERT INTO user_info(`name`, age, create_time) values (?, ?, ?)",
                users.getName(), users.getAge(),users.getCreateTime());

//        int result = usersMapper.save(users);
//        return result>0?users.getId():0;
    }

    @Override
    public int updateById(@PathVariable("id") Long id, @RequestBody Users users) {
        //方法一：Spring Boot使用JDBC操作数据库
        return jdbcTemplate.update("UPDATE user_info SET `name` = ? , age = ? , create_time = ? WHERE id=?",
                users.getName(), users.getAge(), users.getCreateTime(), id);

//        return usersMapper.updateById(id,users);
    }

    @Override
    public int deleteById(@PathVariable("id") Long id) {
        //方法一：Spring Boot使用JDBC操作数据库
        return jdbcTemplate.update("DELETE FROM user_info where id = ? ",id);

//        return usersMapper.deleteById(id);
    }
}
