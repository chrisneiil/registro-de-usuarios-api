package com.evaluacion.java.registroDeUsuariosApi.repository;

import com.evaluacion.java.registroDeUsuariosApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     Busca un usuario por su dirección de correo electrónico
     retorna un optional que contiene el usuario si es que lo encuentra, o un Optional vacio en el caso contrario.
     */
    Optional<User> findByEmail(String email);
}