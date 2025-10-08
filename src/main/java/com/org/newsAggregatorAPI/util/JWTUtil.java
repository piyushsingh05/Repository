package com.org.newsAggregatorAPI.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMillis;

    private SecretKey SECRET_KEY;
//    public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @PostConstruct
    public void init(){
        SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public  String genToken(String userName){
    return Jwts.builder()
            .setSubject(userName)
            .setExpiration(new Date(System.currentTimeMillis()+expirationMillis))
            .setIssuedAt(new Date())
            .signWith(SECRET_KEY , SignatureAlgorithm.HS256)
            .compact();
    }

    public  boolean validateJWTToken(String  authenticationRequest){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(authenticationRequest)
                .getBody();
        return claims.getExpiration().after(new Date());
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // <-- returns username
    }
}
