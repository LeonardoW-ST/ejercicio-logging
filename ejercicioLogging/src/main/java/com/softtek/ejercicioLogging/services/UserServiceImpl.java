package com.softtek.ejercicioLogging.services;

//UserService con createUser(id, name),
// getAllUsers(),
// getUserById(id),
// addBet(userId, bet).

import com.softtek.ejercicioLogging.daos.model.Bet;
import com.softtek.ejercicioLogging.model.User;
import com.softtek.ejercicioLogging.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> createUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> addBet(int userId, Bet bet) {
        return Optional.empty();
    }

}
