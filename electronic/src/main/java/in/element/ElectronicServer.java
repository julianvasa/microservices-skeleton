package in.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.logging.Logger;

@SpringBootApplication
@EnableDiscoveryClient
public class ElectronicServer {

	@Autowired
	protected ElectronicsRepository repository;

	protected Logger logger = Logger.getLogger(ElectronicServer.class.getName());

	public static void main(String[] args) {
		// Tell server to look for bike-server.yml
		System.setProperty("spring.config.name", "electronic-server");
		SpringApplication.run(ElectronicServer.class, args);
	}
}
