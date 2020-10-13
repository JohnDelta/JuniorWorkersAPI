package com.junior_workers.bodies;

public class UserLoginResponse {
	
	private String jwt;
	private String email;
	private String role;
	
	public String getJwt() {
		return jwt;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

}
