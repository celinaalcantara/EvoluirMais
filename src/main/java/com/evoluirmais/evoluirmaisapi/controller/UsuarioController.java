package com.evoluirmais.evoluirmaisapi.controller;

import com.evoluirmais.evoluirmaisapi.dto.UsuarioCadastroDTO;
import com.evoluirmais.evoluirmaisapi.dto.UsuarioResponseDTO;
import com.evoluirmais.evoluirmaisapi.model.Usuario;
import com.evoluirmais.evoluirmaisapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

     // POST p/ realizar o cadastro de um novo usuário
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @RequestBody @Valid UsuarioCadastroDTO dto) {

        // Conversão de DTO p/ entidade Model
        Usuario novoUsuario = convertToEntity(dto);

        // Chamando o service p/ persistência e lógica de negocio
        Usuario usuarioSalvo = usuarioService.cadastrarUsuario(novoUsuario);

        // Conversão de entidade Model p/ DTO de resposta
        UsuarioResponseDTO responseDto = convertToResponseDTO(usuarioSalvo);

        // Resposta HTTP 201
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    private Usuario convertToEntity(UsuarioCadastroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setCpf(dto.cpf());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setCep(dto.cep());
        usuario.setEmail(dto.email());
        usuario.setSenhaCriptografada(dto.senha());
        usuario.setProfissao(dto.profissao());
        usuario.setHabilidades(dto.habilidades());
        usuario.setObjetivos(dto.objetivos());
        return usuario;
    }

    private UsuarioResponseDTO convertToResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getDataNascimento(),
                usuario.getProfissao(),
                usuario.getHabilidades(),
                usuario.getObjetivos()
        );
    }
}