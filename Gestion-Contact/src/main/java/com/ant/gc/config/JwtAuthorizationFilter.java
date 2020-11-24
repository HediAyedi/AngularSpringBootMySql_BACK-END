package com.ant.gc.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Configuration
public class JwtAuthorizationFilter extends OncePerRequestFilter{
	@Value("${jwt.signin-key}")
	private String signinkey;
	@Value("${jwt.header}")
	private String header;
	@Value("${jwt.prefix}")
	private String prefix;
	@Value("${jwt.exp-time}")
	private long expTime;

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	String head  = request.getHeader(header);
	
	if(head == null || !head.startsWith(prefix) ) {
		filterChain.doFilter(request, response);
		return ;
	}
		String token = head.replace(prefix, "");
		
		Claims claims = Jwts.parser().setSigningKey(signinkey.getBytes()).parseClaimsJws(token).getBody();
		
		
		String username = claims.getSubject();
		
		List<String> roles = (List<String>) claims.get("roles");
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			roles.forEach(role ->{
				authorities.add(new SimpleGrantedAuthority(role));
			});
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authToken);
			filterChain.doFilter(request, response);
}
}
