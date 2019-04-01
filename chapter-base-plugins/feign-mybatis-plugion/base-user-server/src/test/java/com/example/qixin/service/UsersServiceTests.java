package com.example.qixin.service;

import com.example.qixin.entity.Users;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersServiceTests {

    @Autowired
    private UsersService usersService;
    @Test
    public void testQuery() {
        List<String> list = mock(List.class);
        Users user = new Users();
        user.setName("小黎");
        user.setAge(34);
        user.setCreateTime(new Date());
        long result = usersService.save(user);
        Assert.assertEquals(3L,result);
    }
}
