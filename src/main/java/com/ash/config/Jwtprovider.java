package com.ash.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class Jwtprovider {

    private static final SecretKey key =
            Keys.hmacShaKeyFor(Jwtconstant.secret_key.getBytes());

    // Generate JWT
    public static String generatetoken(Authentication auth) {

        return Jwts.builder()
                .issuer("CCodewithash")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }

    
    public static String getemailfromjwttoken(String header) {

        if (header.startsWith("Bearer ")) {
            header = header.substring(7);
        }

        Claims claims = Jwts.parser()
                .verifyWith(key)          // ✅ NEW
                .build()
                .parseSignedClaims(header) // ✅ NEW
                .getPayload();

        return claims.get("email", String.class);
    }
}
