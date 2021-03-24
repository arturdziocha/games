package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;
import external.ConsoleModule;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerFacadeTest {

    private PlayerFacade playerFacade;

    @BeforeEach
    void before() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        playerFacade = injector.getInstance(PlayerFacade.class);
    }

    @Test
    @DisplayName("Should create player")
    void test5() {
        // Given
        PlayerType playerType = PlayerType.HUMAN_PLAYER;
        // When
        PlayerCreateDto input = new PlayerCreateDto.Builder().name("Artur").playerType(playerType).build();
        Either<Error, CreateDto> player = playerFacade.create(input);

        // Then
        Either<Error, PlayerDto> output = playerFacade.find(player.get().getId());
        assertThat(output.get().getName()).isEqualTo("Artur");

    }
}
