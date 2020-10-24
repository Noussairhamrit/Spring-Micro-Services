package tn.esprit.spring.service;

import tn.esprit.spring.entity.AppRole;
import tn.esprit.spring.entity.AppUser;

public interface AccountService {
	public AppUser saveUser(String username ,String password,String ConfirmedPassword);
	public AppRole saveRole(AppRole role);
	public AppUser loadUserByUsername(String username);
	public void addRoleToUser(String Username,String rolename); 

}
