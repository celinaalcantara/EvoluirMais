package com.evoluirmais.evoluirmaisapi.controller;

import com.evoluirmais.evoluirmaisapi.dto.LoginRequestDTO;
import com.evoluirmais.evoluirmaisapi.dto.TokenResponseDTO;
import com.evoluirmais.evoluirmaisapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        try {
            String token = authService.realizarLogin(request);
            // retorna o token com status 200
            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (AuthenticationException e) {
            // em caso de credenciais invalidas
            return ResponseEntity.badRequest().build();
        }
    }
}