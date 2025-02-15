package com.howard.esunbanktest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY; // 簽名用的金鑰

    @Value("${jwt.cookie.name}")
    private String JWT_COOKIE_NAME;

    @Value("${jwt.token.claimName}")
    private String JWT_CLAIM_NAME;

    @Value("${jwt.AllowedClockSkewSeconds}")
    private String SKEW_SECONDS;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * 產生 JWT
     * @param phoneNumber 使用者手機號碼
     * @return JWT
     */
    public String generateToken(String phoneNumber, Integer user_id) {

        Instant now = Instant.now();
        ZonedDateTime expirationTime = now.atZone(ZoneId.of("Asia/Taipei"))
                                          .plusHours( 1 ); // 30 minute expiration

        return Jwts.builder()
                   .setSubject(phoneNumber)
                   .claim( JWT_CLAIM_NAME, user_id )
                   .setIssuedAt( Date.from(now) )
                   .setExpiration( Date.from(expirationTime.toInstant() ) )
                   .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                   .compact();
    }

    /**
     * 驗證 token
     * @param token 使用者的 token
     * @return 驗證結果
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .setAllowedClockSkewSeconds( Long.parseLong(SKEW_SECONDS) )
                .build()
                .parseClaimsJws(token);
            return true;
        } catch ( JwtException e ) { // 解析失敗或過期
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 從 token 解析出 subject
     * @param token token
     * @return 使用者的手機號碼
     */
    public String getSubjectFromToken(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getSigningKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }


    public String extractTokenFromRequest(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ( JWT_COOKIE_NAME.equals( cookie.getName() ) ) {
                    return cookie.getValue(); // 取得 JWT Token
                }
            }
        }
        return null; // 沒有 JWT Token
    }

    public Integer extractUserId(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get( JWT_CLAIM_NAME, Integer.class ); // 解析 userId
        } catch (Exception e) {
            return null; // 解析失敗
        }
    }

}
