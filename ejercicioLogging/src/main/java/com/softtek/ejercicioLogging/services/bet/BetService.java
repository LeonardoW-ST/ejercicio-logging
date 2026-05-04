package com.softtek.ejercicioLogging.services.bet;

import com.softtek.ejercicioLogging.services.exceptions.InvalidBetException;

import java.util.List;

public interface BetService {
    void validate(List<Integer> numbers) throws InvalidBetException;
}
