package com.microservice.export;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceExportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceExportApplication.class, args);
	}

}
