package com.softtek.ejercicioLogging.services.user;

import com.softtek.ejercicioLogging.daos.model.Bet;
import com.softtek.ejercicioLogging.daos.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    Optional<User> createUser(User user);
    Optional<User> addBet(int userId, Bet bet);
}
