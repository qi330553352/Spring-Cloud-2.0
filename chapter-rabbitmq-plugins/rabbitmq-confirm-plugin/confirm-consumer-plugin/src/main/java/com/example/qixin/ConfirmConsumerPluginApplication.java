package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfirmConsumerPluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmConsumerPluginApplication.class, args);
	}

}

