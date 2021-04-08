package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    private Validator validator;

    @BeforeEach
    void before() {
        this.validator = new Validator();
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

}
