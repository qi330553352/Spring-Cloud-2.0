package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AdminEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminEurekaApplication.class, args);
	}

}

