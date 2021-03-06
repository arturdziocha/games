package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
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
        Either<Error, PlayerCreateDto> validated = validator.validate(input);
        // Then
        assertThat(validated.getLeft()).isEqualTo(PlayerError.DATA_CANNOT_BE_EMPTY);
    }

    @Test
    @DisplayName("Should return Option<Error> when player name is null")
    void test2() {
        // Given
        PlayerCreateDto input = new PlayerCreateDto.Builder().withName(null).withPlayerType(PlayerType.HUMAN_PLAYER).build();
        // When
        Either<Error, PlayerCreateDto> validated = validator.validate(input);
        // Then
        assertThat(validated.getLeft()).isEqualTo(PlayerError.PLAYER_NAME_CANNOT_BE_EMPTY);
    }

    @Test
    @DisplayName("Should return Option<Error> when player type is null")
    void test3() {
        // Given
        PlayerCreateDto input = new PlayerCreateDto.Builder().withName("Artur").withPlayerType(null).build();
        // When
        Either<Error, PlayerCreateDto> validated = validator.validate(input);
        // Then
        assertThat(validated.getLeft()).isEqualTo(PlayerError.PLAYER_TYPE_CANNOT_BE_EMPTY);
    }

}
