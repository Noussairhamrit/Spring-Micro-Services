package tn.esprit.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder bcpe=getBCPE();
//		auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234")).roles("ADMIN","USER");
//		auth.inMemoryAuthentication().withUser("user1").password(bcpe.encode("1234")).roles("USER");
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*c pas la peine de vérifier a chaque fois si la requete http 
		 * contient le csrf tocken qui tu deja genere auparavant*/
		http.csrf().disable();
		/*ce n'est plus la peine d'utiliser des session*/
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		/*autoriser tout les requete*/
		//http.authorizeRequests().anyRequest().permitAll();
		
		/**/
		
//		http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");
//		http.authorizeRequests().antMatchers("/produit/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/categories/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/produits/**").permitAll();
		//le rest
		http.authorizeRequests().anyRequest().authenticated();
		/**/
		http.addFilterBefore(new JWTAuthorisation(), UsernamePasswordAuthenticationFilter.class);
	}
//	@Bean
//	BCryptPasswordEncoder getBCPE(){
//		return new BCryptPasswordEncoder();
//		
//	}
}
