package com.howard.esunbanktest.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY; // 簽名用的金鑰

    @Value("${jwt.expirationTime:1800000}")
    private long EXPIRATION_TIME; // token 有效期間

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * 產生 JWT
     * @param phoneNumber 使用者手機號碼
     * @return JWT
     */
    public String generateToken(String phoneNumber, Integer user_id) {
        return Jwts.builder()
                   .setSubject(phoneNumber)
                   .claim("userId", user_id)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
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
                .build()
                .parseClaimsJws(token);
            return true;
        } catch ( JwtException e ) { // 解析失敗或過期
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

}
