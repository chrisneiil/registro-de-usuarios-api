package com.evaluacion.java.registroDeUsuariosApi.service;

import com.evaluacion.java.registroDeUsuariosApi.dto.PhoneDto;
import com.evaluacion.java.registroDeUsuariosApi.dto.UserRegistrationRequest;
import com.evaluacion.java.registroDeUsuariosApi.exception.UserAlreadyExistsException;
import com.evaluacion.java.registroDeUsuariosApi.model.User;
import com.evaluacion.java.registroDeUsuariosApi.repository.UserRepository;
import com.evaluacion.java.registroDeUsuariosApi.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRegistrationRequest request;

    @BeforeEach
    void setUp() {
        request = new UserRegistrationRequest();
        request.setName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("Password123");
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("1234567");
        phoneDto.setCitycode("1");
        phoneDto.setContrycode("57");
        request.setPhones(Collections.singletonList(phoneDto));
    }

    @Test
    void registerUser_ShouldSucceed_WhenEmailIsNew() {

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(jwtService.generateToken(anyString())).thenReturn("mocked.jwt.token");
        when(passwordEncoder.encode(anyString())).thenReturn("hashedMockedPassword");

        var response = userService.registerUser(request);


        assertNotNull(response);
        assertEquals("mocked.jwt.token", response.getToken());
        assertTrue(response.isIsactive());

        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_ShouldFail_WhenEmailAlreadyExists() {
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.registerUser(request);
        });

        verify(userRepository, never()).save(any(User.class));
    }
}