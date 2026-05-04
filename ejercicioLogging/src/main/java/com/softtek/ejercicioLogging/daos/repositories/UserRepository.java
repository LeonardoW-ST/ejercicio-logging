package com.softtek.ejercicioLogging.daos.repositories;

import com.softtek.ejercicioLogging.daos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
