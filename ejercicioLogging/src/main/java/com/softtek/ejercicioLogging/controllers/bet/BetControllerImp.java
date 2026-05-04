package com.softtek.ejercicioLogging.controllers.bet;

import com.softtek.ejercicioLogging.daos.model.Bet;
import com.softtek.ejercicioLogging.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/bets")
public class BetControllerImp implements BetController {

    private static final Logger log = LoggerFactory.getLogger(BetControllerImp.class);

    private final UserService userService;

    public BetControllerImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> registerBet(
            @PathVariable int userId,
            @RequestBody List<Integer> numbers) {

        log.info("Petición de registro de apuesta para usuario");
        log.debug("Usuario: {}, apuesta: {}", userId, numbers);

        Bet bet = new Bet(numbers != null ? new ArrayList<>(numbers) : null);
        userService.addBet(userId, bet);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
