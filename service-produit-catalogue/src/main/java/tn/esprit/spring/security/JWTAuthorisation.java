package tn.esprit.spring.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JWTAuthorisation extends OncePerRequestFilter {

	/*vérifier s'il contient jwt 
	 * applel un librairie qui permet de signer
	 * si tout va b1 ....*/
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse respense, FilterChain filterChain)
			throws ServletException, IOException {
		respense.addHeader("Access-Control-Allow-Origin", "*");
		respense.addHeader("Access-Control-Allow-Headers", "Origin, Accect, X-Requested-With, Content-Type, Access-Control-Request-Method ,Access-Control-Request-Headers, authorization ");
		respense.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials ,authorization");
		respense.addHeader("Access-Control-Allow-Methods","GET ,POST,PUT,DELETE,PATCH");

		if (request.getMethod().equals("OPTIONS")) {
			respense.setStatus(HttpServletResponse.SC_OK);	
		}
		else if(request.getRequestURI().equals("/login")){
			filterChain.doFilter(request, respense);
			return;
		}
		else {
			
		String jwtToken=request.getHeader(SecurityParams.JWT_HEADERNAME);
		if(jwtToken==null || !jwtToken.startsWith(SecurityParams.PREFIX)){
			filterChain.doFilter(request, respense);
			return;
		}
		//signer le token
		JWTVerifier jwtVerifier= JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
		//supprimer le prefix
		String jwt=jwtToken.substring(SecurityParams.PREFIX.length());
		DecodedJWT decodedJWT=jwtVerifier.verify(jwt);
		///
		String username=decodedJWT.getSubject();
		List<String>roles=decodedJWT.getClaims().get("roles").asList(String.class);
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		roles.forEach(rn->{
			authorities.add(new SimpleGrantedAuthority(rn));
		});
		///
		UsernamePasswordAuthenticationToken user =new UsernamePasswordAuthenticationToken(username, null,authorities);
		SecurityContextHolder.getContext().setAuthentication(user);
		filterChain.doFilter(request, respense);
		
	}
	}

	
}
