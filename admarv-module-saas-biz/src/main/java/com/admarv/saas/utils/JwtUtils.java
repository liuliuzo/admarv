package com.admarv.saas.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 
 * @author liuliu
 *
 */
public class JwtUtils {

    private static final String SECRET_KEY = "EAAEIQEPl6a0BAJAHpv1urg1jQs4ncS2yv9OIgfzdvlNDF7oLdhGzeElkgl26dbEyocL5uqZBPcE8M3kRGsDLhrjEWpKvNIiwvoYNxsuoxY717sYyJEkgMGRMfZAZA0wOJL2aHpFAvjdQ7FXQP3gxlpGsEYRuuB4I3qgcUE5vYr4HvwpBKZCYUth9gK01LO0QMTTIQ7ZAew4yvkFoLJPTEzJRXD8zwufH0Fi9PlYrPrgZBDK0NW6729";
    private static final long EXPIRATION_TIME_MS = 86400000; // 24 hours

    public static String generateToken(String userId) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS);
        byte[] secretKeyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        Key key = Keys.hmacShaKeyFor(secretKeyBytes);
        return Jwts.builder().setSubject(userId).setExpiration(expirationDate).signWith(key).compact();
    }

    public static boolean validateToken(String token) {
        try {
            byte[] secretKeyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            Key key = Keys.hmacShaKeyFor(secretKeyBytes);
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUsernameFromToken(String token) {
        byte[] secretKeyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        Key key = Keys.hmacShaKeyFor(secretKeyBytes);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
