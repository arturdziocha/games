package com.ara.game.usecases.battleship.dataLoader;

import com.ara.game.usecases.battleship.enums.Direction;
import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.battleship.ship.ShipFacade;
import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.ShipPointsFacade;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

public final class ShipsCreator {
    private final ShipFacade shipFacade;
    private final PointsCreator pointsCreator;
    private final ShipPointsFacade shipPointsFacade;
    private final PointFacade pointFacade;

    @Inject
    ShipsCreator(final ShipGateway shipGateway, final IdGenerator idGenerator, final PointGateway pointGateway,
                 final ShipPointsGateway shipPointsGateway) {
        shipFacade = new ShipFacade(shipGateway, idGenerator);
        pointsCreator = new PointsCreator(pointGateway, idGenerator);
        pointFacade = new PointFacade(pointGateway, idGenerator);
        shipPointsFacade = new ShipPointsFacade(shipPointsGateway);
    }

    public ShipWithPointsDto createBarca1A1Down() {
        PointDto startPoint = pointsCreator.createA1();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.BARCA1).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade
                .create(ShipPointsCreateDto.builder().ship(ship).points(HashSet.of(startPoint)).build())
                .get();
    }

    public ShipWithPointsDto createBarca2C1Down() {
        PointDto startPoint = pointsCreator.createC1();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.BARCA2).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade
                .create(ShipPointsCreateDto.builder().ship(ship).points(HashSet.of(startPoint)).build())
                .get();
    }

    public ShipWithPointsDto createBarca3E1Down() {
        PointDto startPoint = pointsCreator.createE1();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.BARCA3).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade
                .create(ShipPointsCreateDto.builder().ship(ship).points(HashSet.of(startPoint)).build())
                .get();
    }

    public ShipWithPointsDto createPatrolBoat1G1Down() {
        Set<PointDto> points = pointFacade
                .createPoints(PointsCreateDto.builder()
                        .point(pointsCreator.createG1())
                        .size(ShipClass.PATROL_BOAT1.getSize())
                        .direction(Direction.DOWN)
                        .build())
                .get();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.PATROL_BOAT1).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade.create(ShipPointsCreateDto.builder().ship(ship).points(points).build()).get();
    }

    public ShipWithPointsDto createPatrolBoat2I1Down() {
        Set<PointDto> points = pointFacade
                .createPoints(PointsCreateDto.builder()
                        .point(pointsCreator.createI1())
                        .size(ShipClass.PATROL_BOAT2.getSize())
                        .direction(Direction.DOWN)
                        .build())
                .get();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.PATROL_BOAT2).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade.create(ShipPointsCreateDto.builder().ship(ship).points(points).build()).get();
    }

    public ShipWithPointsDto createSubmarineA10Up() {
        Set<PointDto> points = pointFacade
                .createPoints(PointsCreateDto.builder()
                        .point(pointsCreator.createA10())
                        .size(ShipClass.SUBMARINE.getSize())
                        .direction(Direction.UP)
                        .build())
                .get();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.SUBMARINE).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade.create(ShipPointsCreateDto.builder().ship(ship).points(points).build()).get();
    }

    public ShipWithPointsDto createDestroyerC10Up() {
        Set<PointDto> points = pointFacade
                .createPoints(PointsCreateDto.builder()
                        .point(pointsCreator.createC10())
                        .size(ShipClass.DESTROYER.getSize())
                        .direction(Direction.UP)
                        .build())
                .get();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.DESTROYER).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade.create(ShipPointsCreateDto.builder().ship(ship).points(points).build()).get();
    }

    public ShipWithPointsDto createBattleshipE10Up() {
        Set<PointDto> points = pointFacade
                .createPoints(PointsCreateDto.builder()
                        .point(pointsCreator.createE10())
                        .size(ShipClass.BATTLESHIP.getSize())
                        .direction(Direction.UP)
                        .build())
                .get();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.BATTLESHIP).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade.create(ShipPointsCreateDto.builder().ship(ship).points(points).build()).get();
    }

    public ShipWithPointsDto createCarrierJ10Up() {
        Set<PointDto> points = pointFacade
                .createPoints(PointsCreateDto.builder()
                        .point(pointsCreator.createJ10())
                        .size(ShipClass.CARRIER.getSize())
                        .direction(Direction.UP)
                        .build())
                .get();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.CARRIER).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade.create(ShipPointsCreateDto.builder().ship(ship).points(points).build()).get();
    }

    public ShipWithPointsDto createBattleshipToClose() {
        Set<PointDto> points = pointFacade
                .createPoints(PointsCreateDto.builder()
                        .point(pointsCreator.createI10())
                        .size(ShipClass.BATTLESHIP.getSize())
                        .direction(Direction.UP)
                        .build())
                .get();
        ShipDto ship = shipFacade
                .create(ShipCreateDto.builder().shipClass(ShipClass.BATTLESHIP).build())
                .map(l -> shipFacade.find(l.getId()).get())
                .get();
        return shipPointsFacade.create(ShipPointsCreateDto.builder().ship(ship).points(points).build()).get();
    }

    public Set<ShipWithPointsDto> createNineShips() {
        return HashSet
                .of(createBarca1A1Down(), createBarca2C1Down(), createBarca3E1Down(), createPatrolBoat1G1Down(),
                        createPatrolBoat2I1Down(), createSubmarineA10Up(), createDestroyerC10Up(), createBattleshipE10Up(),
                        createCarrierJ10Up());
    }

    public Set<ShipWithPointsDto> createEightShips() {
        return HashSet
                .of(createBarca1A1Down(), createBarca2C1Down(), createBarca3E1Down(), createPatrolBoat1G1Down(),
                        createPatrolBoat2I1Down(), createSubmarineA10Up(), createDestroyerC10Up(), createBattleshipE10Up());
    }
}
