package com.ara.game.usecases.battleship.player;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.control.Either;

class PlayerFacadeTest {

    private PlayerFacade playerFacade;

    @BeforeEach
    void before() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        playerFacade = injector.getInstance(PlayerFacade.class);
    }

    @Test
    @DisplayName("Should return Option<Error> when player name already exists")
    void test4() {
        // Given
        PlayerCreateDto input = new PlayerCreateDto.Builder().name("Artur").playerType(PlayerType.HUMAN_PLAYER).build();
        // When
        playerFacade.create(input);
        Either<Error, PlayerDto> alreadyExists = playerFacade.create(input);

        // Then
        assertThat(alreadyExists.getLeft().getCause()).isEqualTo("Player name already exists");

    }

    @Test
    @DisplayName("Should create player")
    void test5() {
        // Given
        PlayerType playerType = PlayerType.HUMAN_PLAYER;
        // When
        PlayerCreateDto input = new PlayerCreateDto.Builder().name("Artur").playerType(playerType).build();
        Either<Error, PlayerDto> player = playerFacade.create(input);

        // Then
        Either<Error, PlayerDto> output = playerFacade.find(player.get().getId());
        assertThat(output.get().getName()).isEqualTo("Artur");

    }
}
