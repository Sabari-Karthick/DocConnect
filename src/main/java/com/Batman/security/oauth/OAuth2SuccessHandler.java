package com.Batman.security.oauth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Batman.dto.auth.AuthenticationResponse;
import com.Batman.dto.user.UserPrincipal;
import com.Batman.dto.user.UserResponse;
import com.Batman.mapper.CommonMapper;
import com.Batman.security.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private final JwtProvider jwtProvider;
		
	private final ObjectMapper mapper;
  
	private final CommonMapper mapper2;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		 UserPrincipal oAuth2User = (UserPrincipal) authentication.getPrincipal();
    	
    	String email = (String) oAuth2User.getAttributes().get("email");
    	String token = jwtProvider.createToken(email, "USER");
    	
    	AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    	authenticationResponse.setResponse(mapper2.convertToResponse(oAuth2User.getUser(), UserResponse.class));
    	authenticationResponse.setToken(token);
    	String successResponse = mapper.writeValueAsString(authenticationResponse);
    	response.setContentType("application/json");
    	response.setStatus(200);
    	response.getWriter().write(successResponse);
    	response.getWriter().flush();

    	
        
	}

}
