package com.application.security;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;

/**
 * 
 * @author Parautiu JWT handler
 */

public class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 364 * 24 * 60 * 60 * 1000; // one year
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_NAME = "Authorization";
	static final String USER_DETAILS = "User-Details";

	/**
	 * Generate a JWT and add into the header
	 * 
	 * @param res
	 * @param user
	 * @throws JsonProcessingException
	 */
	static void addAuthentication(HttpServletResponse res, Authentication user) throws JsonProcessingException {
		// setting token claims : userName and roles
		Claims claims = Jwts.claims().setSubject(user.getName());
		// map the collection of authorities to string array : the format of role array
		// in token must be [ROLE_USER,ROLE_ADMIN]
		List<String> roles = new ArrayList<>();
		user.getAuthorities().stream().forEach(role -> {
			roles.add(role.getAuthority());
		});
		// add in claims the role array
		claims.put("roles", roles);
		// generate the token
		String JWT = Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		// Add access_token to the header response
		res.addHeader(HEADER_NAME, TOKEN_PREFIX + " " + JWT);
		ObjectMapper mapper = new ObjectMapper();
		// Add into the header extra informations about user
		res.addHeader(USER_DETAILS, mapper.writeValueAsString(user.getDetails()));
	}

	/**
	 * Get the token, parse it and create @UsernamePasswordAuthenticationToken
	 * object using userName and roles
	 * 
	 * @param req
	 * @see parse token and verify it: by username and roles
	 * @return Authentication object
	 */
	static Authentication getAuthentication(HttpServletRequest req) {
		// get token form header request
		String token = req.getHeader(HEADER_NAME);
		// if there is a token parse it
		if (token != null) {
			// get claims
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			// get subject from claims
			String user = claims.getSubject();
			// Get role array of string from claim and parse them to GrantedAuthorities
			ArrayList<String> roles = (ArrayList<String>) claims.get("roles");
			Set<GrantedAuthority> authorities = new HashSet<>();
			roles.stream().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role));
			});
			System.out.println(authorities);
			// if we have claims create UsernamePasswordAuthenticationToken object and pass
			// it to security flow
			if (user != null)
				return new UsernamePasswordAuthenticationToken(user, null, authorities);
			return null;
		}
		return null;
	}
}
