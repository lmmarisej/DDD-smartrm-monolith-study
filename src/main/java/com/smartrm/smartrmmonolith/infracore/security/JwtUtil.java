package com.smartrm.smartrmmonolith.infracore.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;

/**
 * @author: yoda
 * @description:
 */
@Component
public class JwtUtil {
    
    @Value("${jwt.key}")
    private String jwtSecretKey;
    
    @Value("${jwt.expirationSecs:-1}")
    private Long expirationSecs;
    
    private SecretKey secretKey;
    
    @PostConstruct
    private void init() {
        byte[] bytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        secretKey = Keys.hmacShaKeyFor(bytes);
    }
    
    public String createToken(String subject, Map<String, Object> claims) {
        long expiration = expirationSecs * 1000L;
        JwtBuilder builder = Jwts.builder();
        return builder.setIssuer("smartrm")
                .setIssuedAt(new Date())
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .addClaims(claims)
                .signWith(secretKey)
                .compact();
    }
    
    public Claims decode(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }
}
