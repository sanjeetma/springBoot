package com.bridgelabz.fundoo.util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtProvider {
	
		private final long EXPIRATION_TIME = 10000;
		private final String secret = "Sanjeet";

		public String generateToken(String emailId) {
		return JWT.create().withClaim("email", emailId).sign(Algorithm.HMAC512(secret));
		}

		public String parseToken(String token) {
		return JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("email").asString();
		}
		
		public String generateToken(Long Id) {
			return JWT.create().withClaim("id",Id).sign(Algorithm.HMAC512(secret));
			}


			public Long verifyToken(String token) {

			Long id = null;

			if (token != null) {

			id = JWT.require(Algorithm.HMAC512(secret.getBytes())).build().verify(token).getClaim("id").asLong();

			}

			return id;

			}
}
