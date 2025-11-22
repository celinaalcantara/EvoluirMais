package com.evoluirmais.evoluirmaisapi.service;

import com.evoluirmais.evoluirmaisapi.model.Curso;
import com.evoluirmais.evoluirmaisapi.model.Treinamento;
import com.evoluirmais.evoluirmaisapi.model.Palestra;
import com.evoluirmais.evoluirmaisapi.repository.CursoRepository;
import com.evoluirmais.evoluirmaisapi.repository.TreinamentoRepository;
import com.evoluirmais.evoluirmaisapi.repository.PalestraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaConhecimentoService {

    private final CursoRepository cursoRepository;
    private final TreinamentoRepository treinamentoRepository;
    private final PalestraRepository palestraRepository;

    public TrilhaConhecimentoService(CursoRepository cursoRepository,
                                     TreinamentoRepository treinamentoRepository,
                                     PalestraRepository palestraRepository) {
        this.cursoRepository = cursoRepository;
        this.treinamentoRepository = treinamentoRepository;
        this.palestraRepository = palestraRepository;
    }

    // --- Métodos para Consulta da Trilha ---

    public List<Curso> buscarTodosCursos() {
        return cursoRepository.findAll();
    }

    public List<Treinamento> buscarTodosTreinamentos() {
        return treinamentoRepository.findAll();
    }

    public List<Palestra> buscarTodasPalestras() {
        return palestraRepository.findAll();
    }

    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
}