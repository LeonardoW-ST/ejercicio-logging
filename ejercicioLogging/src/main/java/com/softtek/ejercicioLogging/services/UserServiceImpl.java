package com.softtek.ejercicioLogging.services;

//UserService con createUser(id, name),
// getAllUsers(),
// getUserById(id),
// addBet(userId, bet).

import com.softtek.ejercicioLogging.daos.model.Bet;
import com.softtek.ejercicioLogging.model.User;
import com.softtek.ejercicioLogging.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {

        logger.info("Intentando recuperar todos los usuarios");
        List<User> usuarios = userRepository.findAll();

        if (usuarios.isEmpty()) {
            logger.warn("No se han encontrado usuarios en la base de datos");
        } else {
            logger.info("Usuarios recuperados correctamente, total: {}", usuarios.size());
        }

        return usuarios;
    }

    @Override
    public Optional<User> getUserById(String id) {
        logger.info("Intentando recuperar un usuario");
        logger.debug("Datos recibidos para registrar usuario: id={}", id);

        if (!userRepository.existsById(id)) {
            logger.error("No se ha podido recuperar el usuario porque ese id");
            throw new IllegalArgumentException("No existe un usuario con id " + id);
        }
        logger.info("Usuario recuperado correctamente");
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> createUser(String id, User user) {

        logger.info("Intentando registrar un usuario");
        logger.debug("Datos recibidos para registrar usuario: id={}, name={}", id, user.getNombre());

        if (userRepository.existsById(id)) {
            logger.error("No se ha podido registrar el usuario porque el id ya existe");
            throw new IllegalArgumentException("Ya existe un usuario con id " + id);
        }

        user.setId(id);

        logger.info("Usuario registrado correctamente");
        logger.debug("Usuario registrado correctamente: id={}, name={}", user.getId(), user.getNombre());


        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> addBet(int userId, Bet bet) {
        return Optional.empty();
    }

}
