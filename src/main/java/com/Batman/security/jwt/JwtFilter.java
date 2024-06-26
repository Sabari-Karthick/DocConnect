package com.Batman.security.jwt;
/**
 * 
 * 
 * @author _karthick 
 * @date 30/11/23
 * 
 * 
 * 
 * 
 */
import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Batman.advice.Error;
import com.Batman.exceptions.JwtAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter{

	private final JwtProvider provider;
	
	private final ObjectMapper mapper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Entered Authentication Filter.......");
		String token = provider.resolveToken((HttpServletRequest) request);
		try {
		if(token!= null && provider.validateToken(token) ) {
			log.info("Entering Authentication......");
			Authentication authentication = provider.getAuthentication(token);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
		}}
	
		catch (IllegalArgumentException | JwtAuthenticationException e) {
			 SecurityContextHolder.clearContext();
			 
			 Error error = new Error(LocalDateTime.now(),e.getMessage(),"AUTHENTICATION_ERROR");
			 String errorResponse = mapper.writeValueAsString(error);
			 
			 response.setContentType("application/json");
		    	response.setStatus(200);
		    	response.getWriter().write(errorResponse);
		    	response.getWriter().flush();
		    	return;
		}
		filterChain.doFilter(request, response);	
	}
}
