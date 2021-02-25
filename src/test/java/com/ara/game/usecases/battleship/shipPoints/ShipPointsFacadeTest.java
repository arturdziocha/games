package com.ara.game.usecases.battleship.shipPoints;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.enums.Direction;
import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.battleship.ship.ShipFacade;
import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.collection.Set;
import io.vavr.collection.SortedSet;
import io.vavr.control.Either;

public class ShipPointsFacadeTest {
    private ShipPointsFacade shipPointsFacade;
    private PointFacade pointFacade;
    private ShipFacade shipFacade;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        shipPointsFacade = injector.getInstance(ShipPointsFacade.class);
        pointFacade = injector.getInstance(PointFacade.class);
        shipFacade = injector.getInstance(ShipFacade.class);
    }

    @Test
    @DisplayName("Should create Carrier with startPoint A1 and direction down")
    void test1() {
        // Given
        Either<Error, CreateDto> createShip = shipFacade
                .create(new ShipCreateDto.Builder().shipClass(ShipClass.CARRIER).build());
        Either<Error, ShipDto> ship = shipFacade.find(createShip.get().getId());

        Either<Error, CreateDto> createPoint = pointFacade
                .create(new PointCreateRowColDto.Builder().row(0).column(0).build());
        Either<Error, PointDto> startPoint = pointFacade.findById(createPoint.get().getId());

        Either<Error, Set<CreateDto>> points = pointFacade
                .createPoints(new PointsCreateDto.Builder()
                        .point(startPoint.get())
                        .size(ship.get().getHealth())
                        .direction(Direction.DOWN)
                        .build());
        Set<String> pointsIds = points.get().map(CreateDto::getId);

        Either<Error, SortedSet<PointDto>> findPoints = pointFacade.findAllById(pointsIds);
        // When
        shipPointsFacade.create(new ShipPointsCreateDto.Builder().points(findPoints.get()).ship(ship.get()).build());
        Either<Error, ShipPointsDto> shipPoints = shipPointsFacade.find(ship.get().getId());
        Set<String> shipPointsIds = shipPoints.get().getPoints().map(PointDto::getId);
        // Then
        assertThat(pointsIds).containsAll(shipPointsIds);
    }
}
