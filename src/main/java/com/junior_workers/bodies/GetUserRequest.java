package com.junior_workers.bodies;

public class GetUserRequest {
	
	private String jwt;
	private String email;
	
	public String getJwt() {
		return jwt;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
