package com.multiventas.app.security;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.multiventas.app.Constants;
import com.multiventas.app.modelSecurity.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	public static long JWT_TOKEN_VALIDITY = Constants.JWT_TOKEN_VALIDITY;
	public String generate(JwtUser jwtUser) {
		Claims claims = Jwts.claims()
				.setSubject(jwtUser.getUserName());
		claims.put(Constants.USER_ID, String.valueOf(jwtUser.getId()));
		claims.put(Constants.ROLE, jwtUser.getRole());
		

		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS384, Constants.YOUR_SECRET)
				.compact();
		
		
	}
}
