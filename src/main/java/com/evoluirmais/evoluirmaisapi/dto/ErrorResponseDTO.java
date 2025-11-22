package com.evoluirmais.evoluirmaisapi.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String erro,
        String detalhes,
        LocalDateTime timestamp
) {}