package com.example.qixin;

import com.example.qixin.enums.UserSexEnum;
import com.example.qixin.mapper.one.User1Mapper;
import com.example.qixin.mapper.two.User2Mapper;
import com.example.qixin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisMultipleXmlTests {

	@Autowired
	private User1Mapper user1Mapper;
	@Autowired
	private User2Mapper user2Mapper;

	@Test
	public void contextLoads() {
        user1Mapper.insert(new User("aa111", "a123456", UserSexEnum.MAN));
        user1Mapper.insert(new User("bb111", "b123456", UserSexEnum.WOMAN));
        user2Mapper.insert(new User("cc222", "b123456", UserSexEnum.MAN));
	}

}
