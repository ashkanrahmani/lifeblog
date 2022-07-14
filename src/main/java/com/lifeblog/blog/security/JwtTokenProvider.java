package com.lifeblog.blog.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.lifeblog.blog.exception.ApplicationAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currDate = new Date();
        Date expDate = new Date(currDate.getTime() + jwtExpirationInMs);

        return Jwts.builder().setSubject(username).setIssuedAt(currDate).setExpiration(expDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token)  {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, "invalid JWT sig.");
        } catch (MalformedJwtException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, "invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, "expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, "unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, "jwt claims string is empty");
        }
        return true;
    }
}
