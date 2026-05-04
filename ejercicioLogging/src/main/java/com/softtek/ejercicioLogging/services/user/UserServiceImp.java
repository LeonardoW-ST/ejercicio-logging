package com.softtek.ejercicioLogging.services.user;

// UserService con createUser(user),
// getAllUsers(),
// getUserById(id),
// addBet(userId, bet).

import com.softtek.ejercicioLogging.daos.model.Bet;
import com.softtek.ejercicioLogging.daos.model.User;
import com.softtek.ejercicioLogging.daos.repositories.UserRepository;
import com.softtek.ejercicioLogging.services.bet.BetService;
import com.softtek.ejercicioLogging.services.exceptions.UserAlreadyExistsException;
import com.softtek.ejercicioLogging.services.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    private final UserRepository userRepository;
    private final BetService betService;

    public UserServiceImp(UserRepository userRepository, BetService betService) {
        this.userRepository = userRepository;
        this.betService = betService;
    }

    @Override
    public List<User> getAllUsers() {

        log.info("Intentando recuperar todos los usuarios");
        List<User> usuarios = userRepository.findAll();

        if (usuarios.isEmpty()) {
            log.warn("No se han encontrado usuarios en la base de datos");
        } else {
            log.info("Usuarios recuperados correctamente, total: {}", usuarios.size());
        }

        return usuarios;
    }

    @Override
    public Optional<User> getUserById(String id) {
        log.info("Intentando recuperar un usuario");
        log.debug("Datos recibidos para registrar usuario: id={}", id);

        if (!userRepository.existsById(id)) {
            log.error("No se ha podido recuperar el usuario porque ese id");
            throw new IllegalArgumentException("No existe un usuario con id " + id);
        }
        log.info("Usuario recuperado correctamente");
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> createUser(String id, User user) {

        log.info("Intentando registrar un usuario");
        log.debug("Datos recibidos para registrar usuario: id={}, name={}", id, user.getNombre());

        if (userRepository.existsById(id)) {
            log.error("No se ha podido registrar el usuario porque el id ya existe");
            throw new IllegalArgumentException("Ya existe un usuario con id " + id);
        }

        user.setId(id);

        log.info("Usuario registrado correctamente");
        log.debug("Usuario registrado correctamente: id={}, name={}", user.getId(), user.getNombre());


        return Optional.of(userRepository.save(user));
    }


    @Override
    @Transactional
    public Optional<User> addBet(String userId, Bet bet) {
        log.info("Registrando nueva apuesta para un usuario");
        log.debug("userId={}, numbers={}", userId, bet != null ? bet.getNumbers() : null);

        betService.validate(bet != null ? bet.getNumbers() : null);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.info("Intento de apuesta sobre usuario inexistente");
                    log.debug("userId no encontrado: {}", userId);
                    return new UserNotFoundException("Usuario no encontrado: " + userId);
                });

        if (isDuplicate(user, bet.getNumbers())) {
            log.warn("El usuario está registrando una apuesta repetida");
            log.debug("Apuesta duplicada del usuario {}: {}", userId, bet.getNumbers());
        }


        user.addBet(bet);


        User saved = userRepository.save(user);

        log.info("Apuesta registrada correctamente");
        log.debug("Apuesta persistida para usuario {}: {}", userId, bet.getNumbers());

        return Optional.of(saved);
    }

    private boolean isDuplicate(User user, List<Integer> numbers) {
        return user.getBets().stream()
                .anyMatch(existing -> sameCombination(existing.getNumbers(), numbers));
    }

    private boolean sameCombination(List<Integer> a, List<Integer> b) {
        if (a == null || b == null || a.size() != b.size()) return false;
        return new HashSet<>(a).equals(new HashSet<>(b));
    }
}
