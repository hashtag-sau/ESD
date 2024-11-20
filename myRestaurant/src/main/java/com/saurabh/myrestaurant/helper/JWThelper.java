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
    private final String SECRET_KEY = "thisismysecretkeywhichisnotsecret4f8a^2(32f(D*#5fg.maybenow";

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
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Create token with claims
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) // Token valid for 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
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
            String token = authHeader.substring(7); // got the token
            return token;
        }
    }
}
