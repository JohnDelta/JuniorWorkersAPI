package com.junior_workers.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JWTAuthenticate {
	
	public static String getUsername(String jwt) {
		
		Jws<Claims> jws = JWTUtils.parseJWT(jwt);
		
		if(jws != null) {
			return (String) jws.getBody().get("username");
		}
		
		return null;
	}
	
	public static String getMode(String jwt) {
		
		Jws<Claims> jws = JWTUtils.parseJWT(jwt);
		
		if(jws != null) {
			return (String) jws.getBody().get("mode");
		}
		
		return null;
	}

}
