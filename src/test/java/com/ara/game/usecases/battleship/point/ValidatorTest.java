package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {
    private Validator validator;

    @BeforeEach
    public void before() {
        this.validator = new Validator();
    }

    @Test
    @DisplayName("Should return Option<Error> when inputData is null")
    void test1() {
        // Given
        PointCreateStringDto inputData = null;
        // When
        Option<Error> validated = validator.validatePointString(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Data cannot be null");
    }

    @Test
    @DisplayName("Should return Option<Error> when pointString is null")
    void test2() {
        // Given
        PointCreateStringDto inputData = new PointCreateStringDto.Builder().pointString(null).build();
        // When

        Option<Error> validated = validator.validatePointString(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Point string cannot be empty");
    }

    @Test
    @DisplayName("Should return Option<Error> Point String cannot be empty")
    void test3() {
        // Given
        PointCreateStringDto inputData = new PointCreateStringDto.Builder().pointString("").build();
        // When
        Option<Error> validated = validator.validatePointString(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Point string cannot be empty");
    }
    //TODO move other tests

}
