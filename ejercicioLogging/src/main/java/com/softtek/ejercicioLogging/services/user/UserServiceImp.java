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
        log.info("Consultando lista de usuarios");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        log.info("Consultando datos de un usuario");
        log.debug("Buscando usuario con id={}", id);
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> createUser(User user) {
        log.info("Creando un nuevo usuario");
        log.debug("Datos del usuario: id={}, nombre={}", user.getId(), user.getNombre());


        if (user.getId() != null && userRepository.existsById(user.getId())) {
            log.info("Intento de crear un usuario con id ya existente");
            log.debug("Id duplicado: {}", user.getId());
            throw new UserAlreadyExistsException(
                    "Ya existe un usuario con id " + user.getId());
        }

        User saved = userRepository.save(user);
        log.info("Usuario creado correctamente");
        return Optional.of(saved);
    }

    @Override
    @Transactional
    public Optional<User> addBet(int userId, Bet bet) {
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
