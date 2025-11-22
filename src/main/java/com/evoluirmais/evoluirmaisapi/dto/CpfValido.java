package com.evoluirmais.evoluirmaisapi.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfValido {

    String message() default "O CPF é inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}