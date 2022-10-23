package com.microservices.skeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SportServer {

    public static void main(String[] args) {
		// Tell server to look for bike-server.yml
		System.setProperty("spring.config.name", "sport-server");
		SpringApplication.run(SportServer.class, args);
	}
}
