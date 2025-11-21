package com.evoluirmais.evoluirmaisapi.controller;

import com.evoluirmais.evoluirmaisapi.model.Curso;
import com.evoluirmais.evoluirmaisapi.model.Palestra;
import com.evoluirmais.evoluirmaisapi.model.Treinamento;
import com.evoluirmais.evoluirmaisapi.service.CursoService;
import com.evoluirmais.evoluirmaisapi.service.TreinamentoService;
import com.evoluirmais.evoluirmaisapi.service.PalestraService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trilha")
public class TrilhaController {

    private final CursoService cursoService;
    private final PalestraService palestraService;
    private final TreinamentoService treinamentoService;

    public TrilhaController(CursoService cursoService,
                            PalestraService palestraService,
                            TreinamentoService treinamentoService) {
        this.cursoService = cursoService;
        this.palestraService = palestraService;
        this.treinamentoService = treinamentoService;
    }

    // 1. ENDPOINT CURSOS
    @GetMapping("/cursos")
    public ResponseEntity<Page<Curso>> listarCursos(
            @PageableDefault(page = 0, size = 10, sort = "titulo", direction = org.springframework.data.domain.Sort.Direction.ASC)
            Pageable pageable) {

        Page<Curso> cursosPaginados = cursoService.buscarTodos(pageable);
        return ResponseEntity.ok(cursosPaginados);
    }

    // 2. ENDPOINT PALESTRAS
    @GetMapping("/palestras")
    public ResponseEntity<Page<Palestra>> listarPalestras(
            @PageableDefault(page = 0, size = 10, sort = "titulo", direction = org.springframework.data.domain.Sort.Direction.ASC)
            Pageable pageable) {

        Page<Palestra> palestrasPaginadas = palestraService.buscarTodos(pageable);
        return ResponseEntity.ok(palestrasPaginadas);
    }

    // 3. ENDPOINT TREINAMENTOS
    @GetMapping("/treinamentos")
    public ResponseEntity<Page<Treinamento>> listarTreinamentos(
            @PageableDefault(page = 0, size = 10, sort = "titulo", direction = org.springframework.data.domain.Sort.Direction.ASC)
            Pageable pageable) {

        Page<Treinamento> treinamentosPaginados = treinamentoService.buscarTodos(pageable);
        return ResponseEntity.ok(treinamentosPaginados);
    }
}