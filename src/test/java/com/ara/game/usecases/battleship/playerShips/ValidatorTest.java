package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.dataLoader.PlayerLoader;
import com.ara.game.usecases.battleship.dataLoader.ShipsCreator;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipCreateDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;
import external.ConsoleModule;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    private Validator validator;
    private ShipsCreator shipsCreator;
    private PlayerLoader playerLoader;

    @BeforeEach
    void before() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        this.validator = new Validator();
        this.shipsCreator = injector.getInstance(ShipsCreator.class);
        this.playerLoader = injector.getInstance(PlayerLoader.class);
    }

    @Test
    @DisplayName("Should return Option<Error> when inputData is null")
    void test1() {
        // Given
        PlayerShipCreateDto inputData = null;
        // When
        Option<Error> validated = validator.validate(inputData);
        // Then
        assertThat(validated.get()).isEqualTo(PlayerShipError.DATA_NOT_SPECIFIED);
    }

    @Test
    @DisplayName("Should return Option<Error> when playerData is null")
    void test2() {
        // Given
        PlayerShipCreateDto inputData = PlayerShipCreateDto.builder()
                .withPlayer(null)
                .withShip(shipsCreator.createBarca1A1Down())
                .build();
        // When
        Option<Error> validated = validator.validate(inputData);
        // Then
        assertThat(validated.get()).isEqualTo(PlayerShipError.PLAYER_NOT_SPECIFIED);

    }

    @Test
    @DisplayName("Should return Option<Error> when ship data is null")
    void test3() {
        // Given
        PlayerShipCreateDto inputData = PlayerShipCreateDto.builder()
                .withPlayer(playerLoader.loadFirstPlayer())
                .withShip(null)
                .build();
        // When
        Option<Error> validated = validator.validate(inputData);
        // Then
        assertThat(validated.get()).isEqualTo(PlayerShipError.SHIP_NOT_SPECIFIED);

    }

}
