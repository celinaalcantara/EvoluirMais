package com.evoluirmais.evoluirmaisapi.service;

import com.evoluirmais.evoluirmaisapi.dto.LoginRequestDTO;
import com.evoluirmais.evoluirmaisapi.model.Usuario;
import com.evoluirmais.evoluirmaisapi.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService; // buscar o objeto Usuario depois da autenticação

    public AuthService(AuthenticationManager authenticationManager,
                       TokenService tokenService,
                       UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    public String realizarLogin(LoginRequestDTO request) throws AuthenticationException {

        // Criando objeto de autenticação com e-mail e senha
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());

        // Autenticando o usuário (Spring Security p/ verificar a senha no banco)
        Authentication authentication = authenticationManager.authenticate(authToken);

        // Recupera o objeto Usuario p/ passar ao TokenService(se autenticado)
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

        // Gerando o token
        return tokenService.gerarToken(usuarioAutenticado);
    }
}