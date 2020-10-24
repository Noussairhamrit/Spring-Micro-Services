package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.AppUser;
import tn.esprit.spring.service.AccountService;

@RestController
public class UserController {
	@Autowired
	private AccountService accountService;
	@PostMapping("/register")
	
	public AppUser registre(@RequestBody UserForm userForm){
		return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedpassword());
	}
	
	
	 
	
}

class UserForm{
	 private String username;
	 private String password;
	 private String confirmedpassword;
	 
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getConfirmedpassword() {
		return confirmedpassword;
	}
	 
}
