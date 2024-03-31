package com.bookshop.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@PropertySource("application.properties")
public class JwtService {

    static final long EXPIRATION_TIME  = 86400000; // 1 Day in ms
    static final String PREFIX = "Bearer ";
    private SecretKey secretKey;

    @Value("${jwt.key}") // key from application.properties
    private String jwtKey;

    @PostConstruct // delay the creation of secretKey until after jwtKey is available
    public void init() {
        secretKey = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
    }

    public String getToken(String username) {
        try {
            return Jwts.builder()
                    .subject(username)
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(secretKey)
                    .compact();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public String getAuthUser(HttpServletRequest request) {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);

            if(token != null && !token.isEmpty() && token.startsWith(PREFIX)) {
                String user = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token.replace(PREFIX, ""))
                        .getPayload()
                        .getSubject();
                if (user != null) {
                    return user;
                }
            }
        } catch (MalformedJwtException | ExpiredJwtException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (SignatureException signatureException) {
            System.out.println("A key was provided, but could not be verified.");
        }
        return null;
    }
}
