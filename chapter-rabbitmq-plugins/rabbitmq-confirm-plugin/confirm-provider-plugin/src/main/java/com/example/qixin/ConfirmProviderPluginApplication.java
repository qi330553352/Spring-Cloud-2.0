package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfirmProviderPluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmProviderPluginApplication.class, args);
	}

}

