package com.ant.gc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ant.gc.services.UsersService;




@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthentificationFilter jwtAuthentificationFilter;
	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	@Autowired
	private UsersService userService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("admin")
//				.password("admin").authorities("ROLE_Admin");
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("user")
//				.password("user").authorities("ROLE_User");

		
		
		
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.cors();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers("/login").permitAll();

		http.authorizeRequests().anyRequest().authenticated();
		
		http.addFilter(jwtAuthentificationFilter);
		
		http.addFilterAfter(jwtAuthorizationFilter, JwtAuthentificationFilter.class);

	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	web.
	ignoring()
	.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
	"/swagger-ui.html", "/webjars/**");

	}


	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public CorsFilter corsFilter() {
	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	final CorsConfiguration configuration = new CorsConfiguration();
	configuration.setAllowCredentials(true);
	configuration.addAllowedOrigin("*");
	configuration.addAllowedHeader("*");

	configuration.addAllowedMethod("*");

	configuration.addExposedHeader("Authorization");
	source.registerCorsConfiguration("/**", configuration);
	return new CorsFilter(source);
	}	
}
