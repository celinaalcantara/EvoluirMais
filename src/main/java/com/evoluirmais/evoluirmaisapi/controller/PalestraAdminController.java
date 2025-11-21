package com.evoluirmais.evoluirmaisapi.controller;

import com.evoluirmais.evoluirmaisapi.model.Palestra;
import com.evoluirmais.evoluirmaisapi.service.PalestraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/palestras")
public class PalestraAdminController {

    private final PalestraService palestraService;

    public PalestraAdminController(PalestraService palestraService) {
        this.palestraService = palestraService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Palestra> buscarPorId(@PathVariable Long id) {
        Palestra palestra = palestraService.buscarPalestraPorId(id);
        return ResponseEntity.ok(palestra);
    }

    @PostMapping
    public ResponseEntity<Palestra> criar(@RequestBody @Valid Palestra palestra) {
        Palestra novaPalestra = palestraService.salvarPalestra(palestra);
        return new ResponseEntity<>(novaPalestra, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Palestra> atualizar(@PathVariable Long id, @RequestBody @Valid Palestra palestraDetalhes) {
        palestraDetalhes.setId(id);
        Palestra palestraAtualizada = palestraService.salvarPalestra(palestraDetalhes);
        return ResponseEntity.ok(palestraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        palestraService.excluir(id);
        return ResponseEntity.ok("Palestra com ID " + id + " foi excluída com sucesso.");
    }
}