package com.Batman.service.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Batman.dto.user.UserPrincipal;
import com.Batman.model.UserEntity;
import com.Batman.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
	
	private final UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = repository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("NOT_FOUND_USER"));
	
		return new UserPrincipal(user);
	}
}
