package com.saurabh.myrestaurant.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JWThelper {
    private final String SECRET_KEY = "b2fcb37e02a3d482dcb3ea508dc490ab12d68d4cd9f78f3527f9bb06266b923d";

    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 30; // 30 minutes
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7 days

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) //makes sure that only valid tokens are decoded
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {//generic function
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject); //passing getSubject function
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Check if token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generate token
    public String generateAccessToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, ACCESS_TOKEN_EXPIRATION);
    }

    // Generate refresh token
    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, REFRESH_TOKEN_EXPIRATION);
    }

    // Create token with claims and expiration time
    private String createToken(Map<String, Object> claims, String subject, long expirationTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate token
    public Boolean validateToken(String token, String username) {
        return !isTokenExpired(token);
    }

    public Boolean verifyToken(String authHeader){
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // got the token
            String userName = extractUserName(token);

            if(userName == null || !validateToken(token, userName)) {
                return false;
            }

        }
        else{
            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //this will only set 401
           // response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"http header not correct"); //this will also send error message
            return false;
        }
        return true;
    }

    public String getTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // got the token
        }
        return null;
    }

    //although this redundant but its for simplifying access later
    public Boolean isAccessTokenExpired(String token) {
        return isTokenExpired(token);
    }

    public Boolean isRefreshTokenExpired(String token) {
        return isTokenExpired(token);
    }
}
