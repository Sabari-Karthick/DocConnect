package com.Batman.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Batman.security.jwt.JwtFilter;
import com.Batman.security.oauth.CustomOauth2Service;
import com.Batman.security.oauth.OAuth2SuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOauth2Service oauth2Service;

	private final JwtFilter jwtFilter;

	private final OAuth2SuccessHandler handler;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((request) -> request.requestMatchers("/oauth2/**", "/**/*swagger*/**").permitAll()
				.requestMatchers("/api/user/login", "/api/user/register").permitAll()
				.requestMatchers("/api/doctor/all/**").permitAll().anyRequest().authenticated())
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf((csrf) -> csrf.disable())
				.oauth2Login((login) -> login
						.authorizationEndpoint((authorization) -> authorization.baseUri("/oauth2/authorize"))
						.userInfoEndpoint((userInfo) -> userInfo.userService(oauth2Service)).successHandler(handler))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
