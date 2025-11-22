package com.evoluirmais.evoluirmaisapi.service;

import com.evoluirmais.evoluirmaisapi.model.Curso;
import com.evoluirmais.evoluirmaisapi.repository.CursoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Método de LEITURA (GET): Cacheia o resultado da paginação.
    // A chave do cache será baseada nos parâmetros da página, tamanho e ordenação.
    @Cacheable(value = "trilhaCursos", key = "#pageable")
    public Page<Curso> buscarTodos(Pageable pageable) {
        return cursoRepository.findAll(pageable);
    }

    // Método de ESCRITA (POST/PUT): Limpa TODO o cache para forçar a recarga dos dados.
    @Transactional
    @CacheEvict(value = "trilhaCursos", allEntries = true)
    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Método de EXCLUSÃO (DELETE): Busca o curso e limpa o cache.
    @Transactional
    @CacheEvict(value = "trilhaCursos", allEntries = true)
    public void excluir(Long id) {
        // Usa o método buscarCursoPorId para garantir que ele existe (lança 404 se não existir)
        Curso curso = buscarCursoPorId(id);
        cursoRepository.delete(curso);
    }

    // Busca curso por ID (não cacheado, pois é uma consulta rápida e crítica)
    public Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Curso não encontrado com ID: " + id));
    }
}