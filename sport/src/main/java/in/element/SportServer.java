package in.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.logging.Logger;

@SpringBootApplication
@EnableDiscoveryClient
public class SportServer {

	@Autowired
	protected SportsRepository repository;

	protected Logger logger = Logger.getLogger(SportServer.class.getName());

	public static void main(String[] args) {
		// Tell server to look for bike-server.yml
		System.setProperty("spring.config.name", "sport-server");
		SpringApplication.run(SportServer.class, args);
	}
}
