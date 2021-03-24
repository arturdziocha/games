package com.ara.game.usecases.battleship.shipPoints;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.ShipFacade;
import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.collection.HashSet;
import io.vavr.control.Either;
import io.vavr.control.Option;

class ValidatorTest {
    private Validator validator;
    private ShipFacade shipFacade;
    private PointFacade pointFacade;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        shipFacade = injector.getInstance(ShipFacade.class);
        pointFacade = injector.getInstance(PointFacade.class);
        this.validator = new Validator();
    }

    @Test
    @DisplayName("Should return Ship not specified when ship not specified")
    void test1() {
        // Given
        Either<Error, CreateDto> pointId = pointFacade
                .create(new PointCreateStringDto.Builder().pointString("a1").build());
        Either<Error, PointDto> point = pointFacade.findById(pointId.get().getId());
        ShipPointsCreateDto inputData = new ShipPointsCreateDto.Builder()
                .ship(null)
                .points(HashSet.of(point.get()))
                .build();
        // When
        Option<Error> validated = validator.validate(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("Ship not specified");
    }

    @Test
    @DisplayName("Should return No points specified when points is empty")
    void test2() {
        // Given
        Either<Error, CreateDto> shipId = shipFacade
                .create(new ShipCreateDto.Builder().shipClass(ShipClass.BARCA1).build());
        Either<Error, ShipDto> ship = shipFacade.find(shipId.get().getId());
        ShipPointsCreateDto inputData = new ShipPointsCreateDto.Builder()
                .ship(ship.get())
                .points(HashSet.empty())
                .build();
        // When
        Option<Error> validated = validator.validate(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("No points specified");
    }

    @Test
    @DisplayName("Should return No points specified when points is null")
    void test3() {
        // Given
        Either<Error, CreateDto> shipId = shipFacade
                .create(new ShipCreateDto.Builder().shipClass(ShipClass.BARCA1).build());
        Either<Error, ShipDto> ship = shipFacade.find(shipId.get().getId());
        ShipPointsCreateDto inputData = new ShipPointsCreateDto.Builder().ship(ship.get()).points(null).build();
        // When
        Option<Error> validated = validator.validate(inputData);
        // Then
        assertThat(validated.get().getCause()).isEqualTo("No points specified");
    }

}