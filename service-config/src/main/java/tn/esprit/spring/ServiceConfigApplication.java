package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
@EnableConfigServer ////cette annotation est obligtoire pour le service de configuration
@SpringBootApplication
public class ServiceConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConfigApplication.class, args);
	}

}
