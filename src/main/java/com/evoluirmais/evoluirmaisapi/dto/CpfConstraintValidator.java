package com.evoluirmais.evoluirmaisapi.dto;

import com.evoluirmais.evoluirmaisapi.service.CpfValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfConstraintValidator implements ConstraintValidator<CpfValido, String> {

    @Override
    public void initialize(CpfValido constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {

        if (cpf == null || cpf.trim().isEmpty()) {
            return true;
        }

        return CpfValidator.isValidCpf(cpf);
    }
}