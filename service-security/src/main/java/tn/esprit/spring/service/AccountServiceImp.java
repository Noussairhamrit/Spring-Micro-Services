package tn.esprit.spring.service;

import javax.transaction.Transactional;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.AppRoleRepository;
import tn.esprit.spring.dao.AppUserRepository;
import tn.esprit.spring.entity.AppRole;
import tn.esprit.spring.entity.AppUser;
@Service
@Transactional
public class AccountServiceImp implements AccountService{
	
	@Autowired
	AppUserRepository appUserRepository ;
	@Autowired
	AppRoleRepository appRoleRepository ;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder ;

	@Override
	public AppUser saveUser(String username, String password, String ConfirmedPassword) {
		AppUser user =appUserRepository.findByUsername(username);
		if(user!=null)throw new RuntimeException("User already exists");
		if(!password.equals(ConfirmedPassword))throw new RuntimeException("Please confirm password");
		AppUser appuser=new AppUser();
		appuser.setUsername(username);
		appuser.setPassword(bCryptPasswordEncoder.encode(password));
		appuser.setActived(true);
		appUserRepository.save(appuser);
		addRoleToUser(username, "USER");
		
		return appuser;
	}

	@Override
	public AppRole saveRole(AppRole role) {
		
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		
		return appUserRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String Username, String rolename) {
		AppUser appuser=appUserRepository.findByUsername(Username);
		AppRole approle=appRoleRepository.findByRoleName(rolename);
		appuser.getRoles().add(approle);
	}

}
