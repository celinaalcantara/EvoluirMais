package com.evoluirmais.evoluirmaisapi.service;

import com.evoluirmais.evoluirmaisapi.model.*;
import com.evoluirmais.evoluirmaisapi.repository.*;
import com.evoluirmais.evoluirmaisapi.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgressoUsuarioService {

    private final ProgressoUsuarioRepository progressoUsuarioRepository;
    private final CursoRepository cursoRepository;
    private final PalestraRepository palestraRepository;
    private final TreinamentoRepository treinamentoRepository;

    public ProgressoUsuarioService(ProgressoUsuarioRepository progressoUsuarioRepository,
                                   CursoRepository cursoRepository,
                                   PalestraRepository palestraRepository,
                                   TreinamentoRepository treinamentoRepository) {
        this.progressoUsuarioRepository = progressoUsuarioRepository;
        this.cursoRepository = cursoRepository;
        this.palestraRepository = palestraRepository;
        this.treinamentoRepository = treinamentoRepository;
    }

    // 1. Inicializa o progresso para um novo usuário
    @Transactional
    public ProgressoUsuario inicializarProgresso(Usuario usuario) {
        ProgressoUsuario progresso = new ProgressoUsuario();
        progresso.setUsuario(usuario);
        return progressoUsuarioRepository.save(progresso);
    }

    // 2. Marca um item da trilha como concluído e recalcula tudo
    @Transactional
    public ProgressoUsuario marcarComoConcluido(Long userId, String tipoRecurso, Long recursoId) {
        // Busca o progresso do usuário ou lança 404
        ProgressoUsuario progresso = progressoUsuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Progresso do Usuário", userId));

        // Lógica para adicionar o item concluído ao Set correto
        switch (tipoRecurso.toUpperCase()) {
            case "CURSO":
                Curso curso = cursoRepository.findById(recursoId)
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Curso", recursoId));
                progresso.getCursosConcluidos().add(curso);
                break;
            case "PALESTRA":
                Palestra palestra = palestraRepository.findById(recursoId)
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Palestra", recursoId));
                progresso.getPalestrasConcluidas().add(palestra);
                break;
            case "TREINAMENTO":
                Treinamento treinamento = treinamentoRepository.findById(recursoId)
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Treinamento", recursoId));
                progresso.getTreinamentosConcluidos().add(treinamento);
                break;
            default:
                throw new IllegalArgumentException("Tipo de recurso inválido: " + tipoRecurso);
        }

        // 3. Chama o método de cálculo (A lógica principal está aqui)
        recalcularPorcentagens(progresso);

        // 4. Salva o progresso atualizado no banco
        return progressoUsuarioRepository.save(progresso);
    }

    // 4. Lógica de cálculo (Privada)
    private void recalcularPorcentagens(ProgressoUsuario progresso) {

        // Obtem o TOTAL de itens disponíveis no sistema
        long totalCursos = cursoRepository.count();
        long totalPalestras = palestraRepository.count();
        long totalTreinamentos = treinamentoRepository.count();

        // Obtem o TOTAL de itens concluídos pelo usuário
        int concluidosCursos = progresso.getCursosConcluidos().size();
        int concluidosPalestras = progresso.getPalestrasConcluidas().size();
        int concluidosTreinamentos = progresso.getTreinamentosConcluidos().size();

        // --- Cálculo por Categoria ---
        // Cursos
        if (totalCursos > 0) {
            double percentual = ((double) concluidosCursos / totalCursos) * 100.0;
            progresso.setPercentualCursosConcluidos(Math.min(percentual, 100.0));
        } else {
            progresso.setPercentualCursosConcluidos(0.0);
        }

        // Palestras
        if (totalPalestras > 0) {
            double percentual = ((double) concluidosPalestras / totalPalestras) * 100.0;
            progresso.setPercentualPalestrasConcluidas(Math.min(percentual, 100.0));
        } else {
            progresso.setPercentualPalestrasConcluidas(0.0);
        }

        // Treinamentos
        if (totalTreinamentos > 0) {
            double percentual = ((double) concluidosTreinamentos / totalTreinamentos) * 100.0;
            progresso.setPercentualTreinamentosConcluidos(Math.min(percentual, 100.0));
        } else {
            progresso.setPercentualTreinamentosConcluidos(0.0);
        }

        // --- Cálculo do Progresso Geral ---
        long totalItens = totalCursos + totalPalestras + totalTreinamentos;
        long totalConcluido = concluidosCursos + concluidosPalestras + concluidosTreinamentos;

        if (totalItens > 0) {
            double percentualGeral = ((double) totalConcluido / totalItens) * 100.0;
            progresso.setPercentualGeralConclusao(Math.min(percentualGeral, 100.0));
        } else {
            progresso.setPercentualGeralConclusao(0.0);
        }
    }

    public ProgressoUsuario buscarProgressoPorId(Long userId) {
        return progressoUsuarioRepository.findById(userId)
                .orElse(null);
    }

}