package com.evoluirmais.evoluirmaisapi.security;

import com.evoluirmais.evoluirmaisapi.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class TokenService {

    @Value("${api.security.token.secret:suaChaveSecretaPadraoDeveSerBEMMaiorQueIsso}")
    private String secret;

    private static final long EXPIRATION_HOURS = 24;

    public String gerarToken(Usuario usuario) {

        Instant expirationTime = Instant.now().plus(EXPIRATION_HOURS, ChronoUnit.HOURS);
        Date expiryDate = Date.from(expirationTime);
        Key key = getSigningKey();

        // Extrai roles do objeto Usuario
        List<String> roles = usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .claim("email", usuario.getEmail())
                .claim("roles", roles)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validarToken(String token) {
        try {
            Key key = getSigningKey();

            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); // retorna o email
        } catch (Exception e) {
            // Retorna null p/ qualquer exceção
            return null;
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}