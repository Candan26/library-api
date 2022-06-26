package com.siemens.library.api.util;

import com.siemens.library.api.model.RequestBodyAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@Slf4j
public class LibrarianJwtUtil{
    private static final String SECRET_KEY = "librarian";

    @Value("${token.jwt.expiration.time.second}")
    private long expirationDate;

    private Boolean isTokenExpired( String token ){
        try{
            extractExpiration(token).before(new Date());
        }catch (ExpiredJwtException eje){
            log.error("Token expire ", eje);
            return true;
        }catch (Exception ex){
            log.error( "Exception on ", ex);
            return true;
        }
        return false;
    }

    public String generateToken (RequestBodyAuth requestBodyAuth){
        Map<String , Object> claims = new HashMap<>();
        return createToken(claims, requestBodyAuth);
    }

    private String createToken(Map<String, Object> claims, RequestBodyAuth subject) {

        return Jwts.builder().
                setIssuer(subject.getIss()).
                setIssuedAt(new Date(subject.getIat())).
                setExpiration(new Date(subject.getExp())).
                setAudience(subject.getAud()).
                setSubject(subject.getSub()).
                setClaims(claims).
                signWith(SignatureAlgorithm.HS256, SECRET_KEY).
                compact();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token , Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims =  extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token) {
        return isTokenExpired(token);
    }
}
