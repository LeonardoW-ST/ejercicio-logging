package com.softtek.ejercicioLogging.service.bet;

import com.softtek.ejercicioLogging.service.exceptions.InvalidBetException;

import java.util.List;

public interface BetService {
    void validate(List<Integer> numbers) throws InvalidBetException;
}
