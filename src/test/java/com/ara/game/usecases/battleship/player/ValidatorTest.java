package com.ara.game.usecases.battleship.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Option;

@ExtendWith(MockitoExtension.class)
public class ValidatorTest {
    private Validator validator;
    @Mock
    private PlayerGateway playerGateway;

    @BeforeEach
    void before() {
        this.validator = new Validator(playerGateway);
    }

    @Test
    @DisplayName("Should return Option<Error> when input data is null")
    void test1() {
        // Given
        PlayerCreateDto input = null;
        // When
        Option<Error> validated = validator.validate(input);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Data cannot be empty");
    }

    @Test
    @DisplayName("Should return Option<Error> when player name is null")
    void test2() {
        // Given
        PlayerCreateDto input = new PlayerCreateDto.Builder().name(null).playerType(PlayerType.HUMAN_PLAYER).build();
        // When
        Option<Error> validated = validator.validate(input);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Name cannot be empty");
    }

    @Test
    @DisplayName("Should return Option<Error> when player type is null")
    void test3() {
        // Given
        PlayerCreateDto input = new PlayerCreateDto.Builder().name("Artur").playerType(null).build();
        // When
        Option<Error> validated = validator.validate(input);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Type of player cannot be empty");
    }

    @Test
    @DisplayName("Should return Option<Error> when player name already exists")
    void test4() {
        // Given
        PlayerCreateDto input = new PlayerCreateDto.Builder().name("Artur").playerType(PlayerType.HUMAN_PLAYER).build();
        // When
        when(playerGateway.findByName(Mockito.anyString()))
                .thenReturn(Option
                        .some(new PlayerDto.Builder().id("ss").name("ss").playerType(PlayerType.HUMAN_PLAYER).build()));
        Option<Error> validated = validator.validate(input);

        // Then
        assertThat(validated.get().getCause()).isEqualTo("Player name already exists");

    }

}
