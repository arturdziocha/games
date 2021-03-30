package com.ara.game.usecases.battleship.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.dataLoader.PlayerLoader;
import com.ara.game.usecases.battleship.game.dto.GameCreateDto;
import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.dto.GameJoinerDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.collection.HashSet;
import io.vavr.control.Option;

class ValidatorTest {
    private Validator validator;
    private PlayerLoader playerLoader;

    @BeforeEach
    void before() {
        this.validator = new Validator();
        Injector injector = Guice.createInjector(new ConsoleModule());
        this.playerLoader = injector.getInstance(PlayerLoader.class);
    }

    @DisplayName("Should return GameError.DATA_CANNOT_BE_EMPTY when creating game and first player is null")
    @Test
    void test1() {
        // Given
        GameCreateDto inputData = null;
        // When
        Option<Error> validated = validator.validateCreate(inputData);
        // Then
        assertThat(validated.get()).isEqualTo(GameError.DATA_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.PLAYER_DATA_CANNOT_BE_EMPTY when creating and  first player is null")
    @Test
    void test2() {
        // Given
        GameCreateDto inputData = new GameCreateDto.Builder().firstPlayer(null).build();
        // When
        Option<Error> validated = validator.validateCreate(inputData);
        // Then
        assertThat(validated.get()).isEqualTo(GameError.PLAYER_DATA_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.DATA_CANNOT_BE_EMPTY when joining to game and data is null")
    @Test
    void test3() {
        // Given
        GameJoinerDto inputData = null;
        // When
        Option<Error> validated = validator.validateJoin(inputData);
        // Then
        assertThat(validated.get()).isEqualTo(GameError.DATA_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.GAME_CANNOT_BE_EMPTY when joining and  game  is null")
    @Test
    void test4() {
        // Given
        GameJoinerDto inputData = new GameJoinerDto.Builder().game(null).playerToJoin(null).build();
        // When
        Option<Error> validated = validator.validateJoin(inputData);
        // Then
        assertThat(validated.get()).isEqualTo(GameError.GAME_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.PLAYER_DATA_CANNOT_BE_EMPTY when joining and player joiner data is null")
    @Test
    void test5() {
        PlayerDto firstPlayer = playerLoader.loadFirstPlayer();
        GameDto game = new GameDto.Builder()
                .currentPlayer(firstPlayer)
                .currentPlayer(firstPlayer)
                .isStarted(false)
                .players(HashSet.of(firstPlayer))
                .build();
        // Given
        GameJoinerDto inputData = new GameJoinerDto.Builder().game(game).playerToJoin(null).build();
        // When
        Option<Error> validated = validator.validateJoin(inputData);
        // Then
        assertThat(validated.get()).isEqualTo(GameError.PLAYER_DATA_CANNOT_BE_EMPTY);
    }
}
