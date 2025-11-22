package com.evoluirmais.evoluirmaisapi.exception;

import com.evoluirmais.evoluirmaisapi.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Exceções de Validação (Retorno 400 Bad Request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // ... (Lógica de mapeamento de erros de validação, que pode retornar um Map)
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); // 400
    }

    // 2. TRATAMENTO DE OBJETO NÃO ENCONTRADO
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseStatusException(ResponseStatusException ex) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "Recurso Não Encontrado",
                ex.getReason() != null ? ex.getReason() : "Detalhe não disponível.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, ex.getStatusCode()); //  404
    }

    // 3. Exceções de Negócio Customizadas
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailJaCadastrado(EmailJaCadastradoException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Conflito de Dados",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT); // 409
    }

    // 4. Exceção Genérica ( 500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception ex) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "Erro Interno no Servidor",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }

}