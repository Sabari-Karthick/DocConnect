package com.Batman.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Batman.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	Optional<UserEntity> findByEmail(String mail);

}
