package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
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

class PointFacadeTest {

    private PointFacade pointFacade;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        pointFacade = injector.getInstance(PointFacade.class);
    }

    @Test
    @DisplayName("Should return Either.left when inputData is null")
    void test1() {
        // Given
        PointCreateStringDto inputData = null;
        // When
        Either<Error, CreateDto> point = pointFacade.create(inputData);
        // Then
        assertThat(point.getLeft().getCause()).isEqualTo("Data cannot be null");
    }

    @Test
    @DisplayName("Should return Either.left when pointString is null")
    void test2() {
        // Given
        PointCreateStringDto inputData = new PointCreateStringDto.Builder().pointString(null).build();
        // When
        // Then
        Either<Error, CreateDto> point = pointFacade.create(inputData);
        assertThat(point.isLeft()).isTrue();
    }

    @Test
    @DisplayName("Should return Either.left Point String cannot be empty")
    void test3() {
        // Given
        PointCreateStringDto inputData = new PointCreateStringDto.Builder().pointString("").build();
        // When
        Either<Error, CreateDto> point = pointFacade.create(inputData);

        // Then
        assertThat(point.getLeft().getCause()).isEqualTo("Point string cannot be empty");
    }

    @Test
    @DisplayName("Should create and save valid point from string")
    void test4() {
        // Given
        PointCreateStringDto inputData = new PointCreateStringDto.Builder().pointString("a2").build();

        // When
        Either<Error, CreateDto> pointId = pointFacade.create(inputData);
        Either<Error, PointDto> point = pointFacade.findById(pointId.get().getId());
        // Then
        assertThat(point.get().getPointString()).isEqualTo("A2");
        assertThat(point.get().getRow()).isEqualByComparingTo(1);
        assertThat(point.get().getColumn()).isEqualByComparingTo(0);
    }

}
