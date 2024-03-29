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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Batman.advice.Error;
import com.Batman.exceptions.JwtAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter{

	private final JwtProvider provider;
	
	private final ObjectMapper mapper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = provider.resolveToken((HttpServletRequest) request);
		try {
		if(token!= null && provider.validateToken(token) ) {
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
