package com.evaluacion.java.registroDeUsuariosApi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Value("${app.validation.password.regex}")
    private String passwordRegex;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return true;
        }
        return Pattern.compile(passwordRegex)
                .matcher(password)
                .matches();
    }
}