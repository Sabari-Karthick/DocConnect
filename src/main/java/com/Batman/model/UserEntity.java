package com.Batman.model;

import java.util.Set;

import com.Batman.enums.AuthenticationProiver;
import com.Batman.enums.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 40)
	private String name;
	
	@Column(name = "mail",unique = true)
	private String email;
	
	private String password;
	
	
	@Enumerated(EnumType.STRING)
	private AuthenticationProiver provider;
	
	@ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
	

}
