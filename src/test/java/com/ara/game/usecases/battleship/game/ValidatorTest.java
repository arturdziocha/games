package com.ara.game.usecases.battleship.game;

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
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        Either<Error, GameCreateDto> validated = validator.validateCreate(inputData);
        // Then
        assertThat(validated.getLeft()).isEqualTo(GameError.DATA_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.PLAYER_DATA_CANNOT_BE_EMPTY when creating and  first player is null")
    @Test
    void test2() {
        // Given
        GameCreateDto inputData = new GameCreateDto.Builder().firstPlayer(null).build();
        // When
        Either<Error, GameCreateDto> validated = validator.validateCreate(inputData);
        // Then
        assertThat(validated.getLeft()).isEqualTo(GameError.PLAYER_DATA_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.DATA_CANNOT_BE_EMPTY when joining to game and data is null")
    @Test
    void test3() {
        // Given
        GameJoinerDto inputData = null;
        // When
        Either<Error, GameJoinerDto> validated = validator.validateJoin(inputData);
        // Then
        assertThat(validated.getLeft()).isEqualTo(GameError.DATA_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.GAME_CANNOT_BE_EMPTY when joining and  game  is null")
    @Test
    void test4() {
        // Given
        GameJoinerDto inputData = GameJoinerDto.builder().game(null).playerToJoin(null).build();
        // When
        Either<Error, GameJoinerDto> validated = validator.validateJoin(inputData);
        // Then
        assertThat(validated.getLeft()).isEqualTo(GameError.GAME_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.PLAYER_DATA_CANNOT_BE_EMPTY when joining and player joiner data is null")
    @Test
    void test5() {
        PlayerDto firstPlayer = playerLoader.loadFirstPlayer();
        GameDto game = GameDto.builder()
                .currentPlayer(firstPlayer)
                .currentPlayer(firstPlayer)
                .isStarted(false)
                .players(HashSet.of(firstPlayer))
                .build();
        // Given
        GameJoinerDto inputData = GameJoinerDto.builder().game(game).playerToJoin(null).build();
        // When
        Either<Error, GameJoinerDto> validated = validator.validateJoin(inputData);
        // Then
        assertThat(validated.getLeft()).isEqualTo(GameError.PLAYER_DATA_CANNOT_BE_EMPTY);
    }

    @DisplayName("Should return GameError.PLAYER_JOINER_HAS_THE_SAME_ID when joining and player to join is the same as first player")
    @Test
    void test6() {
        PlayerDto firstPlayer = playerLoader.loadFirstPlayer();
        GameDto game = GameDto.builder()
                .id("12345")
                .currentPlayer(firstPlayer)
                .currentPlayer(firstPlayer)
                .isStarted(false)
                .players(HashSet.of(firstPlayer))
                .build();
        // Given
        GameJoinerDto inputData = GameJoinerDto.builder().game(game).playerToJoin(firstPlayer).build();
        // When
        Either<Error, GameJoinerDto> validated = validator.validateJoin(inputData);
        // Then
        assertThat(validated.getLeft()).isEqualTo(GameError.PLAYER_JOINER_HAS_THE_SAME_ID);
    }

    @DisplayName("Should return GameError.TO_SMALL_BOARD when creating the game")
    @Test
    void test7() {
        // Given
        PlayerDto firstPlayer = playerLoader.loadFirstPlayer();
        GameCreateDto inputData = new GameCreateDto.Builder().firstPlayer(firstPlayer).size(7).build();
        // When
        Either<Error, GameCreateDto> validated = validator.validateCreate(inputData);
        // Then
        assertThat(validated.getLeft()).isEqualTo(GameError.TO_SMALL_BOARD_SIZE);
    }

    @DisplayName("Should return GameError.TO_BIG_BOARD_SIZE when creating the game")
    @Test
    void test8() {
        // Given
        PlayerDto firstPlayer = playerLoader.loadFirstPlayer();
        GameCreateDto inputData = new GameCreateDto.Builder().firstPlayer(firstPlayer).size(13).build();
        // When
        Either<Error, GameCreateDto> validated = validator.validateCreate(inputData);
        // Then
        assertThat(validated.getLeft()).isEqualTo(GameError.TO_BIG_BOARD);
    }
}
