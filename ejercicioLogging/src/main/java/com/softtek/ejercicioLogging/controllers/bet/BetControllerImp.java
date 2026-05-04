package com.softtek.ejercicioLogging.controllers.bet;

import com.softtek.ejercicioLogging.services.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/bets")
public class BetControllerImp implements BetController{
    private static final Logger log = LoggerFactory.getLogger(BetController.class);

    private final UserService userService;

    public BetControllerImp(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> registerBet(
            @PathVariable String userId,
            @RequestBody List<Integer> numbers) {

        log.info("Petición de registro de apuesta para usuario");
        log.debug("Usuario: {}, apuesta: {}", userId, numbers);

        userService.addBet(userId, numbers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
