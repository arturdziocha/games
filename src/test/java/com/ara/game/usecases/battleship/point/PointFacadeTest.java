package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.enums.Direction;
import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;
import external.ConsoleModule;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    @DisplayName("Should return Either.left Point string cannot be parsed")
    void test5() {
        // Given
        PointCreateStringDto inputData = new PointCreateStringDto.Builder().pointString("aa2").build();

        // When
        Either<Error, CreateDto> point = pointFacade.create(inputData);

        // Then
        assertThat(point.getLeft().getCause()).isEqualTo("Point string cannot be parsed");
    }

    @Test
    @DisplayName("Should return Either.left When column is null")
    void test6() {
        // Given
        PointCreateRowColDto inputData = new PointCreateRowColDto.Builder().row(1).column(null).build();
        // When
        Either<Error, CreateDto> point = pointFacade.create(inputData);
        // Then
        assertThat(point.getLeft().getCause()).isEqualTo("Column cannot be null");
    }

    @Test
    @DisplayName("Should return Either.left When column is negative")
    void test7() {
        // Given
        PointCreateRowColDto inputData = new PointCreateRowColDto.Builder().row(1).column(-1).build();
        // When
        Either<Error, CreateDto> point = pointFacade.create(inputData);
        // Then
        assertThat(point.getLeft().getCause()).isEqualTo("Column cannot be negative");
    }

    @Test
    @DisplayName("Should create and save valid point from row=1, column=2")
    void test8() {
        // Given
        PointCreateRowColDto inputData = new PointCreateRowColDto.Builder().row(1).column(2).build();

        // When
        Either<Error, CreateDto> pointId = pointFacade.create(inputData);
        Either<Error, PointDto> point = pointFacade.findById(pointId.get().getId());

        // Then
        assertThat(point.get().getPointString()).isEqualTo("C2");
        assertThat(point.get().getRow()).isEqualByComparingTo(1);
        assertThat(point.get().getColumn()).isEqualByComparingTo(2);
    }

    @Test
    @DisplayName("Should return Either.left Wrong column specified when row=40, column=1")
    void test9() {
        // Given
        PointCreateRowColDto inputData = new PointCreateRowColDto.Builder().row(1).column(40).build();
        // When
        Either<Error, CreateDto> pointId = pointFacade.create(inputData);

        // Then
        assertThat(pointId.getLeft().getCause()).isEqualTo("Wrong column specified");
    }

    @Test
    @DisplayName("Should create 4 points down")
    void shouldCreate4PointsDown() {
        // Given
        ShipClass shipClass = ShipClass.BATTLESHIP;
        Direction direction = Direction.DOWN;
        Either<Error, CreateDto> point = pointFacade
                .create(new PointCreateStringDto.Builder().pointString("b2").build());
        Either<Error, PointDto> findPoint = pointFacade.findById(point.get().getId());

        PointsCreateDto spcid = new PointsCreateDto.Builder()
                .size(shipClass.getSize())
                .point(findPoint.get())
                .direction(direction)
                .build();

        // When
        Either<Error, Set<CreateDto>> createdPoints = pointFacade.createPoints(spcid);
        List<String> pointStrings = createdPoints
                .get()
                .map(p -> pointFacade.findById(p.getId()))
                .map(Either::get)
                .map(PointDto::getPointString)
                .toJavaList();

        // Then
        List<String> pStrings = Arrays.asList("B2", "B3", "B4", "B5");
        assertThat(pointStrings).hasSameElementsAs(pStrings);
    }

    @Test
    @DisplayName("Should create 4 points up")
    void shouldCreate4PointsUp() {
        // Given
        ShipClass shipClass = ShipClass.BATTLESHIP;
        Direction direction = Direction.UP;
        Either<Error, CreateDto> point = pointFacade
                .create(new PointCreateStringDto.Builder().pointString("b5").build());
        Either<Error, PointDto> findPoint = pointFacade.findById(point.get().getId());

        PointsCreateDto spcid = new PointsCreateDto.Builder()
                .size(shipClass.getSize())
                .point(findPoint.get())
                .direction(direction)
                .build();

        // When
        Either<Error, Set<CreateDto>> createdPoints = pointFacade.createPoints(spcid);
        List<String> pointStrings = createdPoints
                .get()
                .map(p -> pointFacade.findById(p.getId()))
                .map(Either::get)
                .map(PointDto::getPointString)
                .toJavaList();

        // Then
        List<String> pStrings = Arrays.asList("B2", "B3", "B4", "B5");
        assertThat(pointStrings).hasSameElementsAs(pStrings);
    }
}
