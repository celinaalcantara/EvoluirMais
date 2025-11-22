package com.evoluirmais.evoluirmaisapi.service;

import com.evoluirmais.evoluirmaisapi.model.Palestra;
import com.evoluirmais.evoluirmaisapi.repository.PalestraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PalestraService {

    private final PalestraRepository palestraRepository;

    public PalestraService(PalestraRepository palestraRepository) {
        this.palestraRepository = palestraRepository;
    }

    // Método para salvar palestras
    public Palestra salvarPalestra(Palestra palestra) {
        return palestraRepository.save(palestra);
    }

    // Busca por ID com tratamento 404
    public Palestra buscarPalestraPorId(Long id) {
        return palestraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Palestra não encontrada com ID: " + id));
    }

    // Exclusão com confirmação
    public void excluir(Long id) {
        Palestra palestra = buscarPalestraPorId(id);
        palestraRepository.delete(palestra);
    }
    public Page<Palestra> buscarTodos(Pageable pageable) {
        return palestraRepository.findAll(pageable);
    }

}