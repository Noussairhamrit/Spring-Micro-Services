package tn.esprit.spring.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthorisation extends OncePerRequestFilter {

	/*vérifier s'il contient jwt 
	 * applel un librairie qui permet de signer
	 * si tout va b1 ....*/
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 
		 * */
		String jwt=httpServletRequest.getHeader("Authorization");
		if(jwt==null) throw new RuntimeException("Not Authorize");
		/*
		 * 
		 * 
		 * */
		filterChain.doFilter(httpServletRequest,httpServletResponse );
	}

	
}
