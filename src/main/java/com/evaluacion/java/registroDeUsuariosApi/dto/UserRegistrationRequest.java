package com.evaluacion.java.registroDeUsuariosApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import com.evaluacion.java.registroDeUsuariosApi.validation.ValidPassword;

import java.util.List;

@Data
public class UserRegistrationRequest {

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "El formato del correo es inválido")
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @ValidPassword
    private String password;

    private List<PhoneDto> phones;
}