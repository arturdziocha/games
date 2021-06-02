package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {
    private Validator validator;

    @BeforeEach
    void before() {
        this.validator = new Validator();
    }

    @Test
    @DisplayName("Should return Option<Error> when inputData is null")
    void test1() {
        // Given
        ShipCreateDto createInputData = null;
        // When
        Either<Error, ShipCreateDto> validated = validator.validate(createInputData);
        // Then
        assertThat(validated.getLeft().getCause()).isEqualTo("Data cannot be null");
    }

    @Test
    @DisplayName("Should return Option<Error> when shipClass is null")
    void test2() {
        // Given
        ShipCreateDto createInputData = new ShipCreateDto.Builder().shipClass(null).build();
        // When
        Either<Error, ShipCreateDto> validated = validator.validate(createInputData);
        // Then
        assertThat(validated.getLeft().getCause()).isEqualTo("Class of ship cannot be empty");
    }

}
