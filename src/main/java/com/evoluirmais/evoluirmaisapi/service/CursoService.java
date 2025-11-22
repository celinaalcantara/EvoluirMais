package com.evoluirmais.evoluirmaisapi.service;

import com.evoluirmais.evoluirmaisapi.model.Curso;
import com.evoluirmais.evoluirmaisapi.repository.CursoRepository;
import org.springframework.stereotype.Service;
import com.evoluirmais.evoluirmaisapi.model.Usuario;
import com.evoluirmais.evoluirmaisapi.model.ProgressoUsuario;
import com.evoluirmais.evoluirmaisapi.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Cacheable(value = "cursos", key = "#pageable")
    public Page<Curso> buscarTodos(Pageable pageable) {

        return cursoRepository.findAll(pageable);
    }

    // Busca curso por ID
    public Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Curso não encontrado com ID: " + id));
    }

    // Método de exclusão que usa a busca por ID
    public void excluir(Long id) {
        Curso curso = buscarCursoPorId(id);
        cursoRepository.delete(curso);
    }

    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

}