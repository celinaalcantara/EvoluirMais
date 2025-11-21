package com.evoluirmais.evoluirmaisapi.controller;

import com.evoluirmais.evoluirmaisapi.model.Curso;
import com.evoluirmais.evoluirmaisapi.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/admin/cursos")
public class CursoAdminController {

    private final CursoService cursoService;

    public CursoAdminController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // GET: Busca um Curso pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarCursoPorId(id);
        return ResponseEntity.ok(curso);
    }

    // POST: Cria um novo Curso
    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody @Valid Curso curso) {
        Curso novoCurso = cursoService.salvarCurso(curso);
        return new ResponseEntity<>(novoCurso, HttpStatus.CREATED);
    }

    //PUT: Atualiza um Curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody @Valid Curso cursoDetalhes) {
        cursoDetalhes.setId(id);
        Curso cursoAtualizado = cursoService.salvarCurso(cursoDetalhes);
        return ResponseEntity.ok(cursoAtualizado);
    }

     // DELETE: Exclui um Curso e retorna uma mensagem de confirmação em JSON
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> excluirCurso(@PathVariable Long id) {
        cursoService.excluir(id);

        Map<String, String> response = new HashMap<>();
        response.put("sucesso", "Curso com ID " + id + " foi excluído com sucesso.");

        return ResponseEntity.ok(response);
    }
}