package com.evoluirmais.evoluirmaisapi.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String recurso, Long id) {
        super(recurso + " com o ID " + id + " não encontrado.");
    }

}