package com.neidev.tivia.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.neidev.tivia.domain.core.model.Beneficiario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${api.security.token.secret}")
    private String segredo;

    public String gerarToken(Beneficiario data) {
        try{
            Algorithm algo = Algorithm.HMAC256(segredo);

            return JWT.create()
                    .withIssuer("TiviaTI")
                    .withSubject(data.getLogin())
                    .withExpiresAt(gerarVidaUtilToken())
                    .sign(algo);
        } catch(JWTCreationException e) {
            throw new RuntimeException("Não foi possível gerar o token.");
        }
    }

    public String verificarToken(String token) {
        try {
            Algorithm algo = Algorithm.HMAC256(segredo);

            return JWT.require(algo)
                    .build().verify(token)
                    .getSubject();
        } catch(JWTVerificationException e) {
            throw new RuntimeException("Não foi possível verificar token");
        }
    }

    Instant gerarVidaUtilToken() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(
                        ZoneOffset.of("-03")
                );
    }
}
