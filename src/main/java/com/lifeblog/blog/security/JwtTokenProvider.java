package com.lifeblog.blog.security;

import com.lifeblog.blog.exception.ApplicationAPIException;
import com.lifeblog.blog.exception.ApplicationAPIExceptionMessage;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, ApplicationAPIExceptionMessage.INVALID_JWT_SIGNATURE.getErrorMessage());
        } catch (MalformedJwtException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, ApplicationAPIExceptionMessage.INVALID_JWT_TOKEN.getErrorMessage());
        } catch (ExpiredJwtException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, ApplicationAPIExceptionMessage.EXPIRED_JWT_TOKEN.getErrorMessage());
        } catch (UnsupportedJwtException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, ApplicationAPIExceptionMessage.UNSUPPORTED_JWT_TOKEN.getErrorMessage());
        } catch (IllegalArgumentException e) {
            throw new ApplicationAPIException(HttpStatus.BAD_REQUEST, ApplicationAPIExceptionMessage.CLAIMS_IS_EMPTY.getErrorMessage());
        }
        return true;
    }
}
