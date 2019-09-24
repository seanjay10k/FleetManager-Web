package com.sp.fleetmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile("dev") 
public class SecurityConfiguaration {

	
	// Authentication
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
		{
			auth.inMemoryAuthentication()
						.withUser("user101")
						.password("{noop}password101")
						.roles("USER","ADMIN");
		}
		
		// Authorization
		@Configuration
		@Order(2)                                                        
		public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
			protected void configure(HttpSecurity http) throws Exception {
				http
					.antMatcher("/**")                               
					.authorizeRequests()
						.anyRequest().hasRole("ADMIN")
						.and()
					.httpBasic()
					.and()
					.csrf()
					.disable();
			}
		}
		
		@Configuration   
		@Order(1)
		public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter 
		{	
			@Override
			public void configure(HttpSecurity http) throws Exception
			{
				http.antMatcher("/website/**").authorizeRequests()
							.anyRequest().hasRole("USER")
							
							.and()
							
							.formLogin().loginPage("/website/login.jsp")
										.failureUrl("/website/login.jsp?error=1")
										.loginProcessingUrl("/website/login")
										.permitAll()
										.and()
										.logout()
										.logoutSuccessUrl("/website/vehicles/list.html");
			}
		}
	
	
	
//	
//	
//	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.
//		inMemoryAuthentication()
//			.withUser("user101")
//			.password("{noop}password101")
//			.roles("USER","ADMIN");
//	}
//	
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().anyRequest().permitAll();//authorizeRequests().anyRequest().
////		http.antMatcher("/**").authorizeRequests()
////			.anyRequest().hasRole("USER")
////			.and()
////			.formLogin().loginPage("/login.jsp")
////						.failureUrl("/login.jsp?error=1")
////						.loginProcessingUrl("/login")
////						.permitAll()
////						.and()
////						.logout()
////						.logoutSuccessUrl("/website/vehicle/list.html");
//	}
//	
	
	
}
