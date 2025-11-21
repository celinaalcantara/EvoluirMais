package com.evoluirmais.evoluirmaisapi.controller;

import com.evoluirmais.evoluirmaisapi.model.Treinamento;
import com.evoluirmais.evoluirmaisapi.service.TreinamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/treinamentos")
public class TreinamentoAdminController {

    private final TreinamentoService treinamentoService;

    public TreinamentoAdminController(TreinamentoService treinamentoService) {
        this.treinamentoService = treinamentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treinamento> buscarPorId(@PathVariable Long id) {
        Treinamento treinamento = treinamentoService.buscarTreinamentoPorId(id);
        return ResponseEntity.ok(treinamento);
    }

    @PostMapping
    public ResponseEntity<Treinamento> criar(@RequestBody @Valid Treinamento treinamento) {
        Treinamento novoTreinamento = treinamentoService.salvarTreinamento(treinamento);
        return new ResponseEntity<>(novoTreinamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treinamento> atualizar(@PathVariable Long id, @RequestBody @Valid Treinamento treinamentoDetalhes) {
        treinamentoDetalhes.setId(id);
        Treinamento treinamentoAtualizado = treinamentoService.salvarTreinamento(treinamentoDetalhes);
        return ResponseEntity.ok(treinamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        treinamentoService.excluir(id);
        return ResponseEntity.ok("Treinamento com ID " + id + " foi excluído com sucesso.");
    }
}