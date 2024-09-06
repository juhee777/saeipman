package com.saeipman.app.commom.security;


import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.saeipman.app.member.service.LoginInfoVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	private final Key key;
	private final long accessTokenExpTime;

	public JwtUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration_time}") long accessTokenExpTime) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.accessTokenExpTime = accessTokenExpTime;
	}

	/**
	 * Access Token 생성
	 * 
	 * @param member
	 * @return Access Token String
	 */
	public String createAccessToken(LoginInfoVO  member) {
		return createToken(member, accessTokenExpTime);
	}

	/**
	 * JWT 생성
	 * 
	 * @param member
	 * @param expireTime
	 * @return JWT String
	 */
	private String createToken(LoginInfoVO member, long expireTime) {
		Claims claims = Jwts.claims();
		claims.put("loginId", member.getLoginId());
		claims.put("role", member.getAuth());

		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime tokenValidity = now.plusSeconds(expireTime);

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(Date.from(now.toInstant()))
				.setExpiration(Date.from(tokenValidity.toInstant()))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	/**
	 * Token에서 User ID 추출
	 * 
	 * @param token
	 * @return User ID
	 */
	public String getUserId(String token) {
		return parseClaims(token).get("memberId", String.class);
	}

	/**
	 * JWT 검증
	 * 
	 * @param token
	 * @return IsValidate
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			log.info("Invalid JWT Token", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT Token", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT Token", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT claims string is empty.", e);
		}
		return false;
	}

	/**
	 * JWT Claims 추출
	 * 
	 * @param accessToken
	 * @return JWT Claims
	 */
	public Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
