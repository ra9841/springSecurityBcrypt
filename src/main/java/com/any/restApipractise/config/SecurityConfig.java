package com.any.restApipractise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity               //method level authorization
public class SecurityConfig {
	
	@Bean
//	//authentication
	public UserDetailsService userDetailService(PasswordEncoder encoder) {
//		UserDetails admin=User.withUsername("rabin")
//				.password(encoder.encode("123"))
//				.roles("admin")
//				.build();
//		UserDetails user=User.withUsername("jhon")
//				.password(encoder.encode("456"))
//				.roles("user","admin","hr")
//				.build();
//		return new InMemoryUserDetailsManager(admin,user);
	return new UserInfoUserDetailsService();  //create class of this by hoovering
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		return http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/v/welcome","/v/register").permitAll()//only for getmapping
		.and()
		.authorizeHttpRequests().antMatchers("/v/**")
		.authenticated().and().formLogin()
		.and().build();
		
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
