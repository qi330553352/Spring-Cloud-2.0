package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BaseUserServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(BaseUserServerApplication.class, args);
	}

}

