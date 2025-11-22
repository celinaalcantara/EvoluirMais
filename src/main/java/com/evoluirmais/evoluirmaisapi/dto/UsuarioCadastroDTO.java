package com.evoluirmais.evoluirmaisapi.dto;

import com.evoluirmais.evoluirmaisapi.model.enums.Habilidade;
import com.evoluirmais.evoluirmaisapi.model.enums.ObjetivoPessoal;
import com.evoluirmais.evoluirmaisapi.model.enums.Profissao;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;
import com.evoluirmais.evoluirmaisapi.dto.CpfValido;

public record UsuarioCadastroDTO(

        @NotBlank(message = "O nome completo é obrigatório.")
        String nomeCompleto,

        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00.")
        @NotBlank(message = "O CPF é obrigatório.")
        @CpfValido
        String cpf,

        @NotNull(message = "A data de nascimento é obrigatória.")
        @Past(message = "A data de nascimento deve ser no passado.")
        LocalDate dataNascimento,

        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000.")
        String cep,

        @Email(message = "O formato do e-mail é inválido.")
        @NotBlank(message = "O e-mail é obrigatório.")
        String email,

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        @NotBlank(message = "A senha é obrigatória.")
        String senha,

        @NotNull(message = "A profissão é obrigatória.")
        Profissao profissao,

        @NotEmpty(message = "Selecione pelo menos uma habilidade.")
        Set<Habilidade> habilidades,

        Set<ObjetivoPessoal> objetivos
) {}