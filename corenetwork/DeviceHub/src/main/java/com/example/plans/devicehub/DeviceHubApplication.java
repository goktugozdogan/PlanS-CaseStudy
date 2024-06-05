package com.example.plans.devicehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeviceHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceHubApplication.class, args);
	}

}
