package com.softtek.ejercicioLogging.services.bet;

import com.softtek.ejercicioLogging.services.exceptions.InvalidBetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class BetServiceImp implements BetService{

    private static final Logger log = LoggerFactory.getLogger(BetServiceImp.class);

    private static final int REQUIRED_SIZE = 6;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 49;


    @Override
    public void validate(List<Integer> numbers) throws InvalidBetException {
        log.info("Validando apuesta recibida");
        log.debug("Números a validar: {}", numbers);

        if (numbers == null || numbers.size() != REQUIRED_SIZE) {
            log.error("Apuesta inválida: debe contener exactamente {} números", REQUIRED_SIZE);
            throw new InvalidBetException(
                    "La apuesta debe contener exactamente " + REQUIRED_SIZE + " números");
        }

        for (Integer n : numbers) {
            if (n == null || n < MIN_VALUE || n > MAX_VALUE) {
                log.error("Apuesta inválida: número fuera de rango [{}-{}]", MIN_VALUE, MAX_VALUE);
                throw new InvalidBetException(
                        "Todos los números deben estar entre " + MIN_VALUE + " y " + MAX_VALUE);
            }
        }

        if (new HashSet<>(numbers).size() != REQUIRED_SIZE) {
            log.error("Apuesta inválida: contiene números repetidos");
            throw new InvalidBetException("Los números no pueden repetirse");
        }

        log.info("Apuesta válida");
    }
}
