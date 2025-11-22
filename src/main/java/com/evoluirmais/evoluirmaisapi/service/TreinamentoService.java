package com.evoluirmais.evoluirmaisapi.service;

import com.evoluirmais.evoluirmaisapi.model.Treinamento;
import com.evoluirmais.evoluirmaisapi.repository.TreinamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@Service
public class TreinamentoService {

    private final TreinamentoRepository treinamentoRepository;

    public TreinamentoService(TreinamentoRepository treinamentoRepository) {
        this.treinamentoRepository = treinamentoRepository;
    }

    // Método para salvar treinamentos
    @Transactional
    @CacheEvict(value = "trilhaTreinamentos", allEntries = true)
    public Treinamento salvarTreinamento(Treinamento treinamento) {
        return treinamentoRepository.save(treinamento);
    }

    // Busca por ID com tratamento 404
    public Treinamento buscarTreinamentoPorId(Long id) {
        return treinamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Treinamento não encontrado com ID: " + id));
    }

    // Exclusão com confirmação
    @Transactional
    @CacheEvict(value = "trilhaTreinamentos", allEntries = true)
    public void excluir(Long id) {
        Treinamento treinamento = buscarTreinamentoPorId(id);
        treinamentoRepository.delete(treinamento);
    }

    @Cacheable(value = "trilhaTreinamentos", key = "#pageable")
    public Page<Treinamento> buscarTodos(Pageable pageable) {
        return treinamentoRepository.findAll(pageable);
    }
}