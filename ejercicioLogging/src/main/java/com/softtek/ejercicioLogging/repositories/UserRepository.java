package com.softtek.ejercicioLogging.repositories;

import com.softtek.ejercicioLogging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
