package com.jp.finances.domain.authentication.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.jp.finances.domain.authentication.RefreshToken;
import com.jp.finances.domain.authentication.RefreshTokenRepository;
import com.jp.finances.domain.authentication.TokenService;
import com.jp.finances.domain.user.User;
import com.jp.finances.domain.user.UserService;
import com.jp.finances.infra.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration.minutes}")
    private int jwtExpirationInMinutes;

    @Override
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            log.info("Generating token for userId: {}", user.getId());
            return JWT.create()
                    .withIssuer("finances")
                    .withSubject(user.getEmail()) // Define o assunto do token como o nome de usuário
                    .withExpiresAt(getExpiration(jwtExpirationInMinutes))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            log.error("Error generating token for userId: {}", user.getId(), exception);
            throw new BusinessException("Error generating token");
        }
    }

    public String validateToken(String token) {
        log.info("Validating token...");
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("finances") // Verifica o emissor do token
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            log.info("Token validated successfully");
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            log.error("Invalid token", exception);
            throw new BusinessException("Invalid token");
        }
    }

    @Override
    public String generateRefreshToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            log.info("Generating refresh token for userId: {}", user.getId());
            return JWT.create()
                    .withIssuer("finances")
                    .withSubject(user.getId().toString()) // Define o assunto do token como o nome de usuário
                    .withExpiresAt(getExpiration(jwtExpirationInMinutes * 3))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            log.error("Error generating refresh token for userId: {}", user.getId(), exception);
            throw new BusinessException("Error generating token");
        }
    }

    private Instant getExpiration(Integer minutes) {
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.of("-03:00"));
    }
}
