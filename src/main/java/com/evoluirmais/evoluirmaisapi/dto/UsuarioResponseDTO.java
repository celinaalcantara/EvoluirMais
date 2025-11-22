package com.evoluirmais.evoluirmaisapi.dto;

import com.evoluirmais.evoluirmaisapi.model.enums.Habilidade;
import com.evoluirmais.evoluirmaisapi.model.enums.ObjetivoPessoal;
import com.evoluirmais.evoluirmaisapi.model.enums.Profissao;
import java.time.LocalDate;
import java.util.Set;

public record UsuarioResponseDTO(
        Long id,
        String nomeCompleto,
        String email,
        LocalDate dataNascimento,
        Profissao profissao,
        Set<Habilidade> habilidades,
        Set<ObjetivoPessoal> objetivos
) {}