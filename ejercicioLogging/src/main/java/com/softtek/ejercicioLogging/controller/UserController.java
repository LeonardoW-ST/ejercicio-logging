package com.softtek.ejercicioLogging.controller;


import com.softtek.ejercicioLogging.daos.model.User;
import com.softtek.ejercicioLogging.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        Optional<User> userBuscado =userService.getUserById(id.toUpperCase());
        if(userBuscado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        return userBuscado.get();

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> createUser(@PathVariable String id, @RequestBody User user) {
        Optional<User> userCreado = userService.createUser(id, user);

        if (userCreado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un usuario con ese id"
            );
        }

        return ResponseEntity.
                created(URI.create("/users/" + userCreado.get().getId())).
                body(userCreado.get());
    }




}
