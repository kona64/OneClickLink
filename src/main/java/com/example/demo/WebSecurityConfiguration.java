package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {
	@Value("${admin.username}")
	String adminUsername;
	
	@Value("${admin.password}")
	String adminPassword;
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = 
				User.builder()
				.username(adminUsername)
	            .password(passwordEncoder().encode(adminPassword))
	            .roles("ADMIN")
	            .build();
		return new InMemoryUserDetailsManager(admin);
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/login").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
			.and()
		    	.formLogin()
				.loginPage("/admin/login")
				.failureUrl("/admin/login?error")
				.loginProcessingUrl("/admin/authenticateLogin")
				.permitAll()
				.defaultSuccessUrl("/admin/links", true);
		return http.build();
	}
}
