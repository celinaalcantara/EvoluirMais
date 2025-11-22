package com.evoluirmais.evoluirmaisapi.security;

import com.evoluirmais.evoluirmaisapi.service.UsuarioDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.evoluirmais.evoluirmaisapi.security.TokenService;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioDetailsService userDetailsService;

    public JwtFilter(TokenService tokenService, UsuarioDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        if (token != null) {
            String subject = tokenService.validarToken(token);

            if (subject != null) {
                // Carrega o usuário
                UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

                // Cria a autenticação
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Salva a autenticação no contexto
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}