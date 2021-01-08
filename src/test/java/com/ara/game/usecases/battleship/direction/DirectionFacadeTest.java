package com.ara.game.usecases.battleship.direction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.direction.dto.DirectionDto;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.Seq;
import io.vavr.control.Either;

class DirectionFacadeTest {

    private DirectionFacade directionFacade;

    @BeforeEach
    void setUp() {
        directionFacade = new DirectionFacade();
    }

    @Test
    @DisplayName("Given null value when find by shortName, should return Either.left")
    void test1() {
        // Given When
        Either<Error, DirectionDto> direction = directionFacade.findByShortName(null);
        // Then
        assertThat(direction.getLeft().getCause()).isEqualTo("Value cannot be empty");
    }

    @Test
    @DisplayName("Given empty string when find by shortName, should return Either.left")
    void test2() {
        // Given When
        Either<Error, DirectionDto> direction = directionFacade.findByShortName("");
        // Then
        assertThat(direction.getLeft().getCause()).isEqualTo("Value cannot be empty");
    }

    @Test
    @DisplayName("When given wrong DirectionOutputData then return Either.left")
    void test3() {
        // Given When
        Either<Error, DirectionDto> direction = directionFacade.findByShortName("w");
        // Then
        assertThat(direction.getLeft().getCause()).isEqualTo("Cannot find direction");
    }

    @Test
    @DisplayName("When given good DirectionOutputData short name then return Either.right")
    void test4() {
        // Given When
        Either<Error, DirectionDto> direction = directionFacade.findByShortName("u");
        // Then
        assertThat(direction.get().getName()).isEqualTo("Up");
    }

    @Test
    @DisplayName("When given wrong DirectionOutputData name then return Either.right")
    void test5() {
        // Given When
        Either<Error, DirectionDto> direction = directionFacade.findByName("WEST");
        // Then
        assertThat(direction.getLeft().getCause()).isEqualTo("Cannot find direction");
    }

    @Test
    @DisplayName("When given good DirectionOutputData name then return Either.right")
    void test6() {
        // Given When
        Either<Error, DirectionDto> direction = directionFacade.findByName("Down");
        // Then
        assertThat(direction.get().getName()).isEqualTo("Down");
    }

    @Test
    @DisplayName("Get all directions")
    void test7() {
        // Given When
        Seq<DirectionDto> directions = directionFacade.findAll();
        // Then
        assertThat(directions.size()).isEqualByComparingTo(4);
    }

}
