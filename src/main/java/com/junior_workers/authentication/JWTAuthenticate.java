package com.junior_workers.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JWTAuthenticate {
	
	public static String authenticate(String jwt, String role) {
		
		Jws<Claims> jws = JWTUtils.parseJWT(jwt);
		
		if(jws != null) {
			if(jws.getBody().get("mode").equals(role)) {
				return (String) jws.getBody().get("username");
			}
		}
		
		return null;
	}
	
	public static String authenticate(String jwt) {
		
		Jws<Claims> jws = JWTUtils.parseJWT(jwt);
		
		if(jws != null) {
			return (String) jws.getBody().get("username");
		}
		
		return null;
	}

}
