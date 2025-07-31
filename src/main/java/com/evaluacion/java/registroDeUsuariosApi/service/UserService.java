package com.evaluacion.java.registroDeUsuariosApi.service;

import com.evaluacion.java.registroDeUsuariosApi.dto.UserRegistrationRequest;
import com.evaluacion.java.registroDeUsuariosApi.dto.UserRegistrationResponse;

public interface UserService {

    /* Registra un nuevo usuario en el sistema. */
    UserRegistrationResponse registerUser(UserRegistrationRequest request);
}