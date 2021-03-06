package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
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
        PointCreateStringDto inputData = PointCreateStringDto.builder().withPointString(null).build();
        // When

        Option<Error> validated = validator.validatePointString(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Point string cannot be empty");
    }

    @Test
    @DisplayName("Should return Option<Error> Point String cannot be empty")
    void test3() {
        // Given
        PointCreateStringDto inputData = PointCreateStringDto.builder().withPointString("").build();
        // When
        Option<Error> validated = validator.validatePointString(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Point string cannot be empty");
    }

    @Test
    @DisplayName("Should return Option<Error> Row cannot be parsed")
    void test4() {
        // Given
        PointCreateStringDto inputData = PointCreateStringDto.builder().withPointString("aa2").build();

        // When
        Option<Error> validated = validator.validatePointString(inputData);

        // Then
        assertThat(validated.get().getCause()).isEqualTo("Row cannot be parsed");
    }

    @Test
    @DisplayName("Should return Option<Error> when column is null")
    void test5() {
        // Given
        PointCreateRowColDto inputData = PointCreateRowColDto.builder().withRow(1).withColumn(null).build();
        // When
        Option<Error> validated = validator.validateRowCol(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Column cannot be null");
    }

    @Test
    @DisplayName("Should return Option<Error> when column is negative")
    void test6() {
        // Given
        PointCreateRowColDto inputData = PointCreateRowColDto.builder().withRow(1).withColumn(-1).build();
        // When
        Option<Error> validated = validator.validateRowCol(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Column cannot be negative");
    }

    @Test
    @DisplayName("Should return Option<Error> when row is null")
    void test7() {
        // Given
        PointCreateRowColDto inputData = PointCreateRowColDto.builder().withRow(null).withColumn(1).build();
        // When
        Option<Error> validated = validator.validateRowCol(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Row cannot be null");
    }

    @Test
    @DisplayName("Should return Option<Error> when row is negative")
    void test8() {
        // Given
        PointCreateRowColDto inputData = PointCreateRowColDto.builder().withRow(-1).withColumn(1).build();
        // When
        Option<Error> validated = validator.validateRowCol(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Row cannot be negative");
    }

    @Test
    @DisplayName("Should return Either.left Wrong column specified when column=40, row=1")
    void test9() {
        // Given
        PointCreateRowColDto inputData = PointCreateRowColDto.builder().withRow(1).withColumn(40).build();
        // When
        Option<Error> validated = validator.validateRowCol(inputData);

        // Then
        assertThat(validated.get().getCause()).isEqualTo("Wrong column specified");
    }

}
