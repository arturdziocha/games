package com.ara.game.usecases.battleship.shipPoints;

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
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;
import external.ConsoleModule;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        Either<Error, ShipDto> ship = shipFacade
                .create(ShipCreateDto.builder().withShipClass(ShipClass.CARRIER).build());

        Either<Error, PointDto> startPoint = pointFacade
                .create(PointCreateRowColDto.builder().withRow(0).withColumn(0).build());


        Either<Error, Set<PointDto>> createdPoints = pointFacade
                .createPoints(PointsCreateDto.builder()
                        .withPoint(startPoint.get())
                        .withSize(ship.get().getHealth())
                        .withDirection(Direction.DOWN)
                        .build());
        Set<PointDto> points = createdPoints.get();
        // When
        shipPointsFacade.create(ShipPointsCreateDto.builder().withPoints(points).withShip(ship.get()).build());
        Either<Error, ShipWithPointsDto> shipPoints = shipPointsFacade.find(ship.get().getId());
        Set<PointDto> shipPointsIds = shipPoints.get().getPoints();
        // Then
        assertThat(points).containsAll(shipPointsIds);
    }
}
