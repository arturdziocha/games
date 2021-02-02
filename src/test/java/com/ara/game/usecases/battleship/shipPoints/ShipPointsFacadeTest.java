package com.ara.game.usecases.battleship.shipPoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.direction.DirectionFacade;
import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.battleship.ship.ShipFacade;
import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipClass.ShipClassFacade;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.collection.Set;
import io.vavr.control.Either;

public class ShipPointsFacadeTest {
    private ShipPointsFacade shipPointsFacade;
    private PointFacade pointFacade;
    private ShipFacade shipFacade;
    private ShipClassFacade shipClassFacade;
    private DirectionFacade directionFacade;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        shipPointsFacade = injector.getInstance(ShipPointsFacade.class);
        pointFacade = injector.getInstance(PointFacade.class);
        shipClassFacade = injector.getInstance(ShipClassFacade.class);
        shipFacade = injector.getInstance(ShipFacade.class);
        directionFacade = injector.getInstance(DirectionFacade.class);

    }

    @Test
    @DisplayName("Should create Carrier with startPoint C4 and direction down")
    void test1() {
     // Given
        Either<Error, CreateDto> shipId = shipFacade
                .create(new ShipCreateDto.Builder().shipClassDto(shipClassFacade.findByShortName("c").get()).build());
        Either<Error, ShipDto> ship = shipFacade.find(shipId.get().getId());
        Either<Error, CreateDto> pointId = pointFacade
                .create(new PointCreateRowColDto.Builder().row(1).column(1).build());
        Either<Error, PointDto> point = pointFacade.findById(pointId.get().getId());

        Either<Error, Set<CreateDto>> pointsIds = pointFacade
                .createPoints(new PointsCreateDto.Builder()
                        .point(point.get())
                        .size(ship.get().getShipClassDto().getSize())
                        .direction(directionFacade.findByShortName("d").get())
                        .build());
        // TODO create method find all by Ids
        Either<Error, Set<PointDto>> points = pointFacade.findAllById(pointsIds.get());
     // When
        Set<String> pointIds = pointsIds.get().map(CreateDto::getId);
        shipPointsFacade.create(new ShipPointsCreateDto.Builder().ship(ship.get()).points())
    }
}
