package com.techm.cadt.jwt;

public class GenerateJwtToken {
    public static void main(String[] args) {
    	
    	String username = "testUser";
    	String password = "password";
    	String jwtToken = JwtUtil.generateToken(username,password);
    	System.out.println("JWT Token ::: "+jwtToken);
    	boolean validateFlag = JwtUtil.verifyToken(jwtToken);
    	System.out.println("validateFlag>>>>"+validateFlag);
    
	}
}
