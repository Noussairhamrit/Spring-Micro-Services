package tn.esprit.spring;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.esprit.spring.dao.AppRoleRepository;
import tn.esprit.spring.dao.AppUserRepository;
import tn.esprit.spring.entity.AppRole;
import tn.esprit.spring.service.AccountService;

@SpringBootApplication
public class ServiceSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSecurityApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(AccountService accountService,AppUserRepository appUserRepository,AppRoleRepository appRoleRepository){
		return args->{
			appUserRepository.deleteAll();
			appRoleRepository.deleteAll();
			accountService.saveRole(new AppRole(null,"USER"));
			accountService.saveRole(new AppRole(null,"ADMIN"));
			Stream.of("user1","user2","user3","admin").forEach(un->{
				accountService.saveUser(un, "1234", "1234");
			});
			accountService.addRoleToUser("admin", "ADMIN");
			
		};
	}
	@Bean
	BCryptPasswordEncoder getBCry(){
		return new BCryptPasswordEncoder();
	}

}
