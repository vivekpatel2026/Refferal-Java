package com.refrralApp.refrral.utility;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtility {

    private final String SECRET_KEY = "jfOeBbkQbyJGf32d7KoKqFqZxLLhzHEtM1aB4iKzqgE="; // you can move it to config file
    private final long EXPIRATION_TIME = 3600000; // 1 hour

    // Generate token
    public String generateToken(String emailAndUser) {
        System.out.println("Inside GenrateToken");
        return Jwts.builder()
                .setSubject(emailAndUser)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate token
    public boolean validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extract email
    public String getEmailAndDetailFromToken(String token) {


        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}


