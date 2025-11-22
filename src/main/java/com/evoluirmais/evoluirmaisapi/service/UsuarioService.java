package com.evoluirmais.evoluirmaisapi.service;

import com.evoluirmais.evoluirmaisapi.model.Usuario;
import com.evoluirmais.evoluirmaisapi.model.ProgressoUsuario;
import com.evoluirmais.evoluirmaisapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.evoluirmais.evoluirmaisapi.exception.EmailJaCadastradoException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ProgressoUsuarioService progressoUsuarioService;
    private final PasswordEncoder passwordEncoder;

    // Injeção de dependência via construtor
    public UsuarioService(UsuarioRepository usuarioRepository,
                          ProgressoUsuarioService progressoUsuarioService,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.progressoUsuarioService = progressoUsuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    // Busca Usuário por ID
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + id));
    }

    @Transactional
    public Usuario cadastrarUsuario(Usuario novoUsuario) {

        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isPresent()) {
            throw new EmailJaCadastradoException(novoUsuario.getEmail());
        }

        // Criptografando a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenhaCriptografada());
        novoUsuario.setSenhaCriptografada(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        progressoUsuarioService.inicializarProgresso(usuarioSalvo);

        return usuarioSalvo;
    }

}