package com.microservices.skeleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JewelryServer {

	@Autowired
	protected JewelryRepository repository;
	

	public static void main(String[] args) {
		// Tell server to look for bike-server.yml
		System.setProperty("spring.config.name", "jewelry-server");
		SpringApplication.run(JewelryServer.class, args);
	}
}
