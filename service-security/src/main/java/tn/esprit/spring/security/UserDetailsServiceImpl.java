package tn.esprit.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.AppUser;
import tn.esprit.spring.service.AccountService;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountService accountService;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*il faut retirer les information des user et le mettre dans un nouveau objet 
		 * user gérer par spring  */
		AppUser appuser=accountService.loadUserByUsername(username);
		if(appuser==null) throw new UsernameNotFoundException("Invalid User");
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		appuser.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		return new  User(appuser.getUsername(),appuser.getPassword(),authorities);
	}

}
