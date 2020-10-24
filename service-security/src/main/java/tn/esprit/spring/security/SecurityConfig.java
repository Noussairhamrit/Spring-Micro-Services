package tn.esprit.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/* il faux cree une classe qui implements cette interface */
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* base sur les session */
		// http.formLogin();

		
		
		// ***********************************************//
		/* base sur tocken */
		
		/* il faur desactiver csrf c le input hidden dans le form Login */
		http.csrf().disable();
		
		/* no session */
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilter(new JWTAuthenticationFiltre(authenticationManager()));
		
		
		/// *****************************************************
		//il faut role admin
		http.authorizeRequests().antMatchers("/appUsers/**").hasAuthority("ADMIN");
		//login et register sans authentification
		http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
		//le reste avec authentification
		http.authorizeRequests().anyRequest().authenticated();
	}

}
