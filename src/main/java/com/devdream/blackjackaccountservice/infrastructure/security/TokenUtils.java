package com.devdream.blackjackaccountservice.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZ(n";
    private final static Long ACCESS_TOKEN_VALIDATE_SECONDS = 86_400L;
    private static Date expirationDate;

    public static String createToken(String name, String email) {
        long expirationTime = ACCESS_TOKEN_VALIDATE_SECONDS * 1000;
        expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            return  new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }
        catch (JwtException e){
            return null;
        }
    }
    public static Date getExpirationDate(){
        return expirationDate;
    }

}
