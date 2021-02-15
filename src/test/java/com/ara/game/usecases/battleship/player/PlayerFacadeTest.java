package com.ara.game.usecases.battleship.player;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerType.PlayerTypeFacade;
import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;
import com.ara.game.usecases.common.CreateDto;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.control.Either;
import com.ara.game.usecases.common.Error;
class PlayerFacadeTest {

    private PlayerFacade playerFacade;
    private PlayerTypeFacade playerTypeFacade;

    @BeforeEach
    void before() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        playerFacade = injector.getInstance(PlayerFacade.class);
        playerTypeFacade = new PlayerTypeFacade();
    }
    @Test
    @DisplayName("Should return Either.left when input data is null")
    void test1() {
        // Given
        PlayerCreateDto input = null;
        // When
        Either<Error, CreateDto> player = playerFacade.create(input);
        // Then
        assertThat(player.getLeft().getCause()).isEqualTo("Data cannot be empty");
    }

    @Test
    @DisplayName("Should return Either.left when player name is null")
    void test2() {        
        // Given
        Either<Error, PlayerTypeDto> playerType = playerTypeFacade.findById("1");
        PlayerCreateDto input = new PlayerCreateDto.Builder().name(null).playerType(playerType.get()).build();
        // When
        Either<Error, CreateDto> player = playerFacade.create(input);
        // Then
        assertThat(player.getLeft().getCause()).isEqualTo("Name cannot be empty");
    }
    @Test
    @DisplayName("Should return Either.left when player type is null")
    void test3() {
        // Given
        PlayerCreateDto input = new PlayerCreateDto.Builder().name("Artur").playerType(null).build();
        // When
        Either<Error, CreateDto> player = playerFacade.create(input);
        // Then
        assertThat(player.getLeft().getCause()).isEqualTo("Type of player cannot be empty");
    }    

    @Test
    @DisplayName("Should return Either.left when player type is not found")
    void test45() {
        // Given
        PlayerCreateDto input = new PlayerCreateDto.Builder().name("Artur").playerType(null).build();
        // When
        Either<Error, CreateDto> player = playerFacade.create(input);
        // Then
        assertThat(player.getLeft().getCause()).isEqualTo("Type of player cannot be empty");
    }

    @Test
    @DisplayName("Should create player")
    void test5() {
        // Given
        Either<Error, PlayerTypeDto> playerType = playerTypeFacade.findById("1");
        // When
        PlayerCreateDto input = new PlayerCreateDto.Builder()
                .name("Artur")
                .playerType(playerType.get())
                .build();
        Either<Error, CreateDto> player = playerFacade.create(input);

        // Then
        Either<Error, PlayerDto> output = playerFacade.find(player.get().getId());
        assertThat(output.get().getName()).isEqualTo("Artur");

    }
}
