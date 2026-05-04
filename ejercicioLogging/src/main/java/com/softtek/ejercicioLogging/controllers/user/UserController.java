package com.softtek.ejercicioLogging.controllers.user;

import com.softtek.ejercicioLogging.daos.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    ResponseEntity<User> createUser(int id, User user);
    ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<User> getUserById(int id);
}
