package com.sportseventapplication.DTO;

import com.sportseventapplication.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor

public class UserDTO {
	private long id;
	private String username;
	private String email;
	private String token;
	private Role role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public UserDTO() {
		super();
	}
	public UserDTO(long id, String username, String email, Role role) {
		super();
		this.id = id;
		this.username = username;	
		this.email = email;
		this.role = role;
	}
	
	
}
