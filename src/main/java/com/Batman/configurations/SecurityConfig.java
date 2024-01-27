package com.Batman.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.Batman.security.jwt.JwtConfigurer;
import com.Batman.security.oauth.CustomOauth2Service;
import com.Batman.security.oauth.OAuth2SuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	    prePostEnabled = true, securedEnabled = false, jsr250Enabled = true
	)
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOauth2Service oauth2Service;

	private final JwtConfigurer configurer;

	private final OAuth2SuccessHandler handler;
	


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		     .csrf().disable().authorizeRequests()
             .and()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
		     .authorizeRequests()
		     .antMatchers("/oauth2/**", "/**/*swagger*/**").permitAll()
		     .antMatchers("/api/user/login","/api/user/register").permitAll()
		     .antMatchers("/api/doctor/all/**").permitAll()
		     .anyRequest().authenticated()
		     .and()
		     .oauth2Login()
		     .authorizationEndpoint().baseUri("/oauth2/authorize")
		     .and()
             .userInfoEndpoint().userService(oauth2Service)
             .and()
             .successHandler(handler)
             .and()
             .apply(configurer);
             
		     
		  
	
		return http.build();
	}
}
