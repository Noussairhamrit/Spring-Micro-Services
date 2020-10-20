package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/*Pourque cette application devienne un  Service Registration (Enregistrement des services), 
vous devez utiliser  @EnableEurekaServer. Cette annotation informe à  Spring d'exécuter un service d'enregistrement par la technologie de 
Netflix (Netflix Eureka).*/

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistreApplication.class, args);
	}

}
