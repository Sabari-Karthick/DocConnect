package com.Batman.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.Batman.enums.AuthenticationProiver;
import com.Batman.enums.Role;

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
