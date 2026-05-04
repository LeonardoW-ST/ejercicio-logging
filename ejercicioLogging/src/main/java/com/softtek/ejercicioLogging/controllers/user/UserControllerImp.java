package com.softtek.ejercicioLogging.controllers.user;

import com.softtek.ejercicioLogging.daos.model.User;
import com.softtek.ejercicioLogging.services.exceptions.UserNotFoundException;
import com.softtek.ejercicioLogging.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerImp implements UserController {

    private static final Logger log = LoggerFactory.getLogger(UserControllerImp.class);

    private final UserService userService;

    public UserControllerImp(UserService userService) {
        this.userService = userService;
    }

    /**
     * PUT /users/{id}
     * Body: {"nombre":"Francisco Sánchez Gómez"}
     * 201 si se crea, 409 si el id ya existe.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<User> createUser(@PathVariable int id, @RequestBody User user) {
        log.info("Petición de registro de usuario");
        log.debug("PUT /users/{} con body {}", id, user);

        // El id viene en la URL. Se lo asignamos a la entidad para garantizarlo.
        user.setId(id);

        User saved = userService.createUser(user)
                .orElseThrow(() -> new IllegalStateException("No se pudo crear el usuario"));

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * GET /users
     * 200 con la lista (vacía si no hay).
     */
    @Override
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Petición de listado de usuarios");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * GET /users/{id}
     * 200 con el usuario y sus apuestas, 404 si no existe.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        log.info("Petición de datos de un usuario");
        log.debug("GET /users/{}", id);

        User user = userService.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado: " + id));

        return ResponseEntity.ok(user);
    }
}
