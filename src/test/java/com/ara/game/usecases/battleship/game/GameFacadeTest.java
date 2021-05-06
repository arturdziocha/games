package com.ara.game.usecases.battleship.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import io.vavr.control.Either;

class GameFacadeTest {
    private GameFacade gameFacade;
    private PlayerLoader playerLoader;

    @BeforeEach
    void before() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        gameFacade = injector.getInstance(GameFacade.class);
        playerLoader = injector.getInstance(PlayerLoader.class);
    }

    @Test
    @DisplayName("Should create game")
    void test1() {
        // Given
        PlayerDto firstPlayer = playerLoader.loadFirstPlayer();
        GameCreateDto inputData = new GameCreateDto.Builder().firstPlayer(firstPlayer).size(10).build();
        // When
        Either<Error, GameDto> created = gameFacade.create(inputData);
        // Then
        assertTrue(created.isRight());

    }

    @Test
    @DisplayName("Should join to created game")
    void test2() {
        // Given
        PlayerDto firstPlayer = playerLoader.loadFirstPlayer();
        PlayerDto secondPlayer = playerLoader.loadSecondPlayer();
        GameCreateDto inputData = new GameCreateDto.Builder().firstPlayer(firstPlayer).size(10).build();
        Either<Error, GameDto> game = gameFacade.create(inputData);

        GameJoinerDto joiner = new GameJoinerDto.Builder().game(game.get()).playerToJoin(secondPlayer).build();
        //When
        Either<Error, GameDto> joined = gameFacade.join(joiner);
        // Then
        assertTrue(joined.isRight());
        assertThat(joined.get().getPlayers().size()).isEqualByComparingTo(2);
        assertThat(joined.get().getCurrentPlayer()).isEqualTo(firstPlayer);
        assertThat(joined.get().getId()).isEqualTo(game.get().getId());

    }

}
