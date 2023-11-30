package com.abhishek.blogapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.abhishek.blogapi.blog.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // or any other customization
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(httpBasic -> httpBasic.realmName("xyz"));

        return http.build();
    }
    @Bean
	 public AuthenticationManager authManager(HttpSecurity http) 
	   throws Exception {
	     return http.getSharedObject(AuthenticationManagerBuilder.class)
	       .userDetailsService(this.customUserDetailService)
	       .passwordEncoder(bCryptPasswordEncoder())
	       .and()
	       .build();
	 }
     @Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
