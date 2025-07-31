package com.evaluacion.java.registroDeUsuariosApi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class) // 1. Conecta la anotación con su lógica de validación
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    // Mensaje de error por defecto
    String message() default "La contraseña no cumple con los requisitos de formato.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}