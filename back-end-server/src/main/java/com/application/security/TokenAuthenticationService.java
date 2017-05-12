package com.application.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static java.util.Collections.emptyList;

/**
 * 
 * @author Parautiu
 * @Doc : used to handle token : creation and verification of matching using secret
 */

public class TokenAuthenticationService {
	
	static final long EXPIRATIONTIME=364*24*60*60*1000; // one year
	static final String SECRET="ThisIsASecret";
	static final String TOKEN_PREFIX="Bearer";
	static final String HEADER_NAME="Authorization";
	
	static void addAuthentication(HttpServletResponse res,String username){
		//setting TOKEn 
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		//Add token to header response
		res.addHeader(HEADER_NAME, TOKEN_PREFIX+" "+JWT);
	}
	
	/**
	 * 
	 * @param req
	 * @see Check if we have the token in header and also we are checking the matching
	 * @return Authentication object
	 */
	static Authentication getAuthentication(HttpServletRequest req){
		String token = req.getHeader(HEADER_NAME);
		if(token!=null){
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX,""))
					.getBody()
					.getSubject();
			return user!=null?new UsernamePasswordAuthenticationToken(user, null, emptyList()):null;
		}
		return null;
	}
}
