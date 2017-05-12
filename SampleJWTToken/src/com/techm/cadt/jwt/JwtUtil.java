package com.techm.cadt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUtil {
	
	static String SECRET_KEY="my_jwt_token_secret";
	public static String generateToken(String username, String password) {
		String jwtToken = null;
		JwtBuilder builder = Jwts.builder().setSubject(String.valueOf(username)).setIssuer(SECRET_KEY)
				.claim("password", password)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY);
		jwtToken = builder.compact();       
		//setIssuedAt(new Date())
		return jwtToken;
	}
	
	public static boolean verifyToken(String jwtToken){
		boolean verificationFlag = false;
		
    	Jws<Claims> claims;
		try {
			claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
			String subject = claims.getBody().getSubject();
			System.out.println("subject>>>>"+subject);
			if (subject.matches("testUser")) {
				System.out.println("User Authenticated");
				verificationFlag = true;
			} else {
				System.out.println("User not Authenticated.");
			}
		} catch (ExpiredJwtException | UnsupportedJwtException
				| MalformedJwtException | SignatureException
				| IllegalArgumentException  e) {
			
			verificationFlag = false;
			e.printStackTrace();
		}
    	return verificationFlag;
	}
}
