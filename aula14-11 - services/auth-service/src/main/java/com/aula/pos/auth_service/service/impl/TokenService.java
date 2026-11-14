package com.aula.pos.auth_service.service.impl;

import com.aula.pos.auth_service.exception.TokenException;
import com.aula.pos.auth_service.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {

    private final String secret;

    public TokenService(@Value("${api.security.token.secret}") String secret) {
        if (secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("JWT signing secret must be at least 32 bytes");
        }
        this.secret = secret;
    }

    public String generateToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("app-auth")
                    .withSubject(usuario.getId().toString())
                    .withClaim("email", usuario.getEmail())
                    .withClaim("role", usuario.getRole().toString())
                    .withExpiresAt(expires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new TokenException("Error ao gerar Token");
        }
    }

    public DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("app-auth")
                .build()
                .verify(token);
    }

    private Instant expires() {
        return LocalDateTime.now().plusMinutes(30).atZone(ZoneId.of("-03:00")).toInstant();
    }

}
