package com.evaluacion.java.registroDeUsuariosApi.service;

// En el paquete com.evaluacion.java.registroDeUsuariosApi.service

import com.evaluacion.java.registroDeUsuariosApi.dto.UserRegistrationRequest;
import com.evaluacion.java.registroDeUsuariosApi.dto.UserRegistrationResponse;
import com.evaluacion.java.registroDeUsuariosApi.exception.UserAlreadyExistsException;
import com.evaluacion.java.registroDeUsuariosApi.model.Phone;
import com.evaluacion.java.registroDeUsuariosApi.model.User; // <-- ¡ESTA ES LA LÍNEA CORRECTA!
import com.evaluacion.java.registroDeUsuariosApi.repository.UserRepository;
import com.evaluacion.java.registroDeUsuariosApi.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El correo ya registrado");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Phone> phones = request.getPhones().stream().map(phoneDto -> {
            Phone phone = new Phone();
            phone.setNumber(phoneDto.getNumber());
            phone.setCitycode(phoneDto.getCitycode());
            phone.setCountrycode(phoneDto.getContrycode());
            phone.setUser(user);
            return phone;
        }).collect(Collectors.toList());
        user.setPhones(phones);

        user.setActive(true);
        user.setLastLogin(LocalDateTime.now());

        final String token = jwtService.generateToken(user.getEmail());
        user.setToken(token);


        User savedUser = userRepository.save(user);


        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setId(savedUser.getId());
        response.setCreated(savedUser.getCreated());
        response.setModified(savedUser.getModified());
        response.setLast_login(savedUser.getLastLogin());
        response.setToken(savedUser.getToken());
        response.setIsactive(savedUser.isActive());

        return response;
    }
}