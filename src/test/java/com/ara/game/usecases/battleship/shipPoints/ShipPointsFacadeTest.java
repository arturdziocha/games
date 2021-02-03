package com.ara.game.usecases.battleship.shipPoints;

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
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
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

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("Should create Carrier with startPoint A1 and direction down")
    void test1() {
        //Given
        Either<Error, CreateDto> createShip =
                shipFacade.create(new ShipCreateDto.Builder().shipClassDto(shipClassFacade.findByShortName("c").get()).build());
        Either<Error, ShipDto> ship = shipFacade.find(createShip.get().getId());

        Either<Error, CreateDto> createPoint =
                pointFacade.create(new PointCreateRowColDto.Builder().row(0).column(0).build());
        Either<Error, PointDto> startPoint = pointFacade.findById(createPoint.get().getId());

        Either<Error, Set<CreateDto>> points =
                pointFacade.createPoints(new PointsCreateDto.Builder()
                        .point(startPoint.get())
                        .size(ship.get().getHealth())
                        .direction(directionFacade.findByShortName("d").get())
                        .build());
        Set<String> pointsIds = points.get().map(CreateDto::getId);

        Either<Error, Set<PointDto>> findPoints = pointFacade.findAllById(pointsIds);
        //When
        shipPointsFacade.create(new ShipPointsCreateDto.Builder().points(findPoints.get()).ship(ship.get()).build());        
        Either<Error, ShipPointsDto> shipPoints = shipPointsFacade.find(ship.get().getId());        
        Set<String> shipPointsIds = shipPoints.get().getPoints().map(PointDto::getId);
        //Then
        assertThat(pointsIds).containsAll(shipPointsIds);


    }
}
