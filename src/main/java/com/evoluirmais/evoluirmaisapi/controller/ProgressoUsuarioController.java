package com.evoluirmais.evoluirmaisapi.controller;

import com.evoluirmais.evoluirmaisapi.model.ProgressoUsuario;
import com.evoluirmais.evoluirmaisapi.service.ProgressoUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progresso")
public class ProgressoUsuarioController {

    private final ProgressoUsuarioService progressoService;

    public ProgressoUsuarioController(ProgressoUsuarioService progressoService) {
        this.progressoService = progressoService;
    }

    // buscar o progresso atual de um usuário
    @GetMapping("/{userId}")
    public ResponseEntity<ProgressoUsuario> buscarProgresso(@PathVariable Long userId) {

        ProgressoUsuario progresso = progressoService.buscarProgressoPorId(userId);

        if (progresso == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(progresso);
    }

     // Endpoint para marcar um item como concluído
    @PostMapping("/{userId}/concluir")
    public ResponseEntity<ProgressoUsuario> marcarConcluido(
            @PathVariable Long userId,
            @RequestParam String tipoRecurso,
            @RequestParam Long recursoId) {

        ProgressoUsuario progressoAtualizado = progressoService.marcarComoConcluido(userId, tipoRecurso, recursoId);

        return ResponseEntity.ok(progressoAtualizado);
    }
}