package com.softtek.ejercicioLogging.controllers.bet;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BetController {
    ResponseEntity<Void> registerBet(int id, List<Integer> numbers);
}
