package com.ara.game.usecases.battleship.playerShips;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipCreateDto;
@ExtendWith(MockitoJUnitRunner.class)
class ValidatorTest {
    
    private Validator validator;
    @BeforeEach
    void before() {
        this.validator = new Validator();
    }
    @Test
    @DisplayName("Should return Option<Error> when inputData is null")
    void test1() {
        PlayerShipCreateDto inputData = new PlayerShipCreateDto.Builder().
    }

}
