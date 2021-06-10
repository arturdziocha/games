package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.enums.Direction;
import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;
import external.ConsoleModule;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
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
    @DisplayName("Should create and save valid point from string")
    void test1() {
        // Given
        PointCreateStringDto inputData = PointCreateStringDto.builder().withPointString("a2").build();

        // When
        Either<Error, PointDto> point = pointFacade.create(inputData);
        // Then
        assertThat(point.get().getPointString()).isEqualTo("A2");
        assertThat(point.get().getRow()).isEqualByComparingTo(1);
        assertThat(point.get().getColumn()).isEqualByComparingTo(0);
    }

    @Test
    @DisplayName("Should create and save valid point from row=1, column=2")
    void test2() {
        // Given
        PointCreateRowColDto inputData = PointCreateRowColDto.builder().withRow(1).withColumn(2).build();

        // When
        Either<Error, PointDto> point = pointFacade.create(inputData);

        // Then
        assertThat(point.get().getPointString()).isEqualTo("C2");
        assertThat(point.get().getRow()).isEqualByComparingTo(1);
        assertThat(point.get().getColumn()).isEqualByComparingTo(2);
    }

    @Test
    @DisplayName("Should create 4 points down")
    void shouldCreate4PointsDown() {
        // Given
        ShipClass shipClass = ShipClass.BATTLESHIP;
        Direction direction = Direction.DOWN;
        Either<Error, PointDto> point = pointFacade
                .create(PointCreateStringDto.builder().withPointString("b2").build());
        PointsCreateDto spcid = PointsCreateDto.builder()
                .withSize(shipClass.getSize())
                .withPoint(point.get())
                .withDirection(direction)
                .build();

        // When
        Either<Error, Set<PointDto>> createdPoints = pointFacade.createPoints(spcid);
        Set<String> pointStrings = createdPoints.get().map(PointDto::getPointString);

        // Then
        Set<String> pStrings = HashSet.of("B2", "B3", "B4", "B5");
        assertThat(pointStrings).hasSameElementsAs(pStrings);
    }

    @Test
    @DisplayName("Should create 4 points up")
    void shouldCreate4PointsUp() {
        // Given
        ShipClass shipClass = ShipClass.BATTLESHIP;
        Direction direction = Direction.UP;
        Either<Error, PointDto> point = pointFacade
                .create(PointCreateStringDto.builder().withPointString("b5").build());
        Either<Error, PointDto> findPoint = pointFacade.findById(point.get().getId());

        PointsCreateDto spcid = PointsCreateDto.builder()
                .withSize(shipClass.getSize())
                .withPoint(findPoint.get())
                .withDirection(direction)
                .build();

        // When
        Either<Error, Set<PointDto>> createdPoints = pointFacade.createPoints(spcid);
        Set<String> pointStrings = createdPoints.get().map(PointDto::getPointString);

        // Then
        Set<String> pStrings = HashSet.of("B2", "B3", "B4", "B5");
        assertThat(pointStrings).hasSameElementsAs(pStrings);
    }
}
