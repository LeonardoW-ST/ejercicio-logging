package com.softtek.ejercicioLogging.daos.repositories;

import com.softtek.ejercicioLogging.daos.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet , Long> {
}
