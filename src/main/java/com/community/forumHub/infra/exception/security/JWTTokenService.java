package com.community.forumHub.infra.exception.security;

import com.community.forumHub.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTTokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "API forumHub";
    private static final int EXPIRATION_HOURS = 48;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT);
            return jwt.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid or expired token!", e);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(EXPIRATION_HOURS)
                .toInstant(ZoneOffset.UTC);
    }
}
