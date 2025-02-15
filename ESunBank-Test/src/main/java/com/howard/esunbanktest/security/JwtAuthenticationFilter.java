package com.howard.esunbanktest.security;

import com.howard.esunbanktest.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.cookie.name}")
    private String JWT_COOKIE_NAME;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 嘗試從 Request Header 取得 "Authorization" : Bearer <token>
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if ( authHeader != null && authHeader.startsWith("Bearer ") ) {
            token = authHeader.substring(7);
        }

        // 嘗試從 Cookie 取 JWT
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( JWT_COOKIE_NAME.equals( cookie.getName() ) ) {
                    token = cookie.getValue();
                    System.out.println("token = " + token);
                }
            }
        }

        // 驗證 token
        if ( token != null && jwtUtil.validateToken(token) ) {
            //  解析出使用者資訊
            String phoneNumber = jwtUtil.getSubjectFromToken(token);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(phoneNumber,
                                                  null,
                                                            Collections.emptyList());

            // 放進 SecurityContext，表示已通過驗證
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        // 繼續 filter 鏈
        filterChain.doFilter(request, response);

    } // doFilterInternal end

} // JwtAuthenticationFilter end
