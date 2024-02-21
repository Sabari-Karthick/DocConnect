package com.Batman.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOauth2Service oauth2Service;

	private final JwtConfigurer configurer;

	private final OAuth2SuccessHandler handler;
	


	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .antMatchers("/oauth2/**", "/**/*swagger*/**").permitAll()
                        .antMatchers("/api/user/login", "/api/user/register").permitAll()
                        .antMatchers("/api/doctor/all/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(login -> login
                        .authorizationEndpoint().baseUri("/oauth2/authorize")
                        .and()
                        .userInfoEndpoint().userService(oauth2Service)
                        .and()
                        .successHandler(handler))
                .apply(configurer);
             
		     
		  
	
		return http.build();
	}
}
