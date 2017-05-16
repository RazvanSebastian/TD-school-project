package com.application.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	static final String USER_DETAILS="User-Details";
	
	static void addAuthentication(HttpServletResponse res,Authentication user) throws JsonProcessingException{
		//setting TOKEn 
		String JWT = Jwts.builder()
				.setSubject(user.getName())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		//Add token to header response
		res.addHeader(HEADER_NAME, TOKEN_PREFIX+" "+JWT);
		ObjectMapper mapper = new ObjectMapper();
		 //as name of USER-DETAILS what am I mapping on front-end i get string map of user details
		res.addHeader(USER_DETAILS, mapper.writeValueAsString(user.getDetails()));
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
