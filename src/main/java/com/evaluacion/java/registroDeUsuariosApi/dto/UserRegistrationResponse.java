package com.evaluacion.java.registroDeUsuariosApi.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserRegistrationResponse {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;
    private boolean isactive;
}