package com.evaluacion.java.registroDeUsuariosApi.controller;

// En el paquete com.evaluacion.java.registroDeUsuariosApi.controller

import com.evaluacion.java.registroDeUsuariosApi.dto.UserRegistrationRequest;
import com.evaluacion.java.registroDeUsuariosApi.dto.UserRegistrationResponse;
import com.evaluacion.java.registroDeUsuariosApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request) {


        UserRegistrationResponse response = userService.registerUser(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}