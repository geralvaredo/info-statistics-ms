package com.stadistic.infostatisticsms.utils;

import com.stadistic.infostatisticsms.dto.AuthenticationRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "012345678998765432100123456789987654321001234567899876543210012345678998765432100123456";
    private static final long EXPIRATION_TIME = 86400000;

    public static String generateToken(AuthenticationRequest authenticationRequest) {
        return Jwts.builder()
                .setSubject(authenticationRequest.getEmail())
                .claim("username", authenticationRequest.getEmail())
                .claim("password", authenticationRequest.getPassword())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String getEmailByToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().get("email", String.class);
    }


}
