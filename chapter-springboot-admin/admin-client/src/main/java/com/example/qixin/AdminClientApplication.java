package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@EnableEurekaClient
@SpringBootApplication
public class AdminClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminClientApplication.class, args);
	}


	@RequestMapping("/test")
	public Map<String,Object> test(){
		Map<String,Object> map = new HashMap<>();
		map.put("msg","123");
		return map;
	}
}

