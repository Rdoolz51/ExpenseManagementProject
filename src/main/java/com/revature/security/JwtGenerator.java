package com.revature.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtGenerator {

    private SecretKey secretKey =  Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication){

        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + (1000 * 60 * 60 * 24));


        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)// We need a secret key which SHOULD NOT be shared, otherwise bad
                .compact();

        return token;
    }

    // Validate an existing JWT
    public boolean validateToken(String token){

        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("JWT token is expired or invalid");
        }

    }

    public String getUsernameFromToken(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
