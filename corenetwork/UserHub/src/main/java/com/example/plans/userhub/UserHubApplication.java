package com.example.plans.userhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class UserHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserHubApplication.class, args);
	}

}
