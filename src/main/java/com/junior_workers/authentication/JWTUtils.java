package com.junior_workers.authentication;

import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

public class JWTUtils {
	
	private static String secret = Encoders.BASE64.encode("12345678910111213141516171819202122232425262272829303132".getBytes());

	private static Key getSigningKey() {
	  byte[] keyBytes = Decoders.BASE64.decode(secret);
	  return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public static String createJWT(String id, String username, String mode, String issuer, String subject, long ttlMillis) {
		
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
	    JwtBuilder builder = Jwts.builder().setId(id)
	    							.claim("username", username)
	    							.claim("mode", mode)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(getSigningKey());
	 
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    return builder.compact();
	}
	 
	public static Jws<Claims> parseJWT(String jwt) {
		
		Jws<Claims> jws = null;

		try {
		    jws = Jwts.parserBuilder()
		    .setSigningKey(getSigningKey())
		    .build()
		    .parseClaimsJws(jwt);
		}	     
		catch (JwtException e) {
			System.out.println("Error in JWTUtils.java | Exception message: " + e.getMessage());
		    return null;
		}
	
		return jws;
	}

}





