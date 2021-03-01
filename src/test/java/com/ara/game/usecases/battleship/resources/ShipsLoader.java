package com.ara.game.usecases.battleship.resources;

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
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.collection.*;

public final class ShipsLoader {
    private final ShipFacade shipFacade;
    private final PointsCreator pointsCreator;
    private final ShipPointsFacade shipPointsFacade;
    private final PointFacade pointFacade;

    @Inject
    ShipsLoader(final ShipGateway shipGateway, final IdGenerator idGenerator, final PointGateway pointGateway,
            final ShipPointsGateway shipPointsGateway) {
        shipFacade = new ShipFacade(shipGateway, idGenerator);
        pointsCreator = new PointsCreator(pointGateway, idGenerator);
        pointFacade = new PointFacade(pointGateway, idGenerator);
        shipPointsFacade = new ShipPointsFacade(shipPointsGateway);
    }

    public Set<ShipPointsDto> loadSix() {
        Set<String> pointsIds = pointsCreator.createSixPoints();
        Array<PointDto> points = pointFacade.findAllById(pointsIds).get().toArray();

        ShipCreateDto[] ships = { new ShipCreateDto.Builder().shipClass(ShipClass.BARCA1).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.BARCA2).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.BARCA3).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.PATROL_BOAT1).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.PATROL_BOAT2).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.SUBMARINE).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.DESTROYER).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.BATTLESHIP).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.CARRIER).build() };
        Set<String> shipsIds = Stream.of(ships).map(s -> shipFacade.create(s).get()).map(CreateDto::getId).toSet();
        Array<ShipDto> findShips = shipsIds.map(id -> shipFacade.find(id).get()).toSortedSet().toArray();
       

        Set<PointsCreateDto> w = HashSet.empty();
        for (int i = 0; i < 6; i++) {
            w = w
                    .add(new PointsCreateDto.Builder()
                            .direction(Direction.DOWN)
                            .point(points.get(i))
                            .size(findShips.get(i).getHealth())
                            .build());
        }
        for (int i = 6; i < findShips.size(); i++) {
            w = w
                    .add(new PointsCreateDto.Builder()
                            .direction(Direction.UP)
                            .point(points.get(i))
                            .size(findShips.get(i).getHealth())
                            .build());
        }
        Set<Set<String>> pSet = w.map(s -> pointFacade.createPoints(s).get()).map(q -> q.map(CreateDto::getId)).toSet();
        Array<SortedSet<PointDto>> ppp = pSet.map(f -> pointFacade.findAllById(f).get()).toArray();
    
        Set<ShipPointsDto> toReturn = HashSet.empty();
        for (int i = 0; i < ppp.size(); i++) {
            toReturn = toReturn
                    .add(shipPointsFacade
                            .create(new ShipPointsCreateDto.Builder().ship(findShips.get(i)).points(ppp.get(i)).build())
                            .get());
        }       
        return toReturn;
    }

    public Set<ShipPointsDto> loadFive() {
        Set<String> pointsIds = pointsCreator.createFivePoints();
        Array<PointDto> points = pointFacade.findAllById(pointsIds).get().toArray();

        ShipCreateDto[] ships = { new ShipCreateDto.Builder().shipClass(ShipClass.BARCA1).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.BARCA2).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.BARCA3).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.PATROL_BOAT1).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.PATROL_BOAT2).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.SUBMARINE).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.DESTROYER).build(),
                new ShipCreateDto.Builder().shipClass(ShipClass.BATTLESHIP).build() };
        Set<String> shipsIds = Stream.of(ships).map(s -> shipFacade.create(s).get()).map(CreateDto::getId).toSet();
        Array<ShipDto> findShips = shipsIds.map(id -> shipFacade.find(id).get()).toSortedSet().toArray();
        System.out.println(findShips);

        Set<PointsCreateDto> w = HashSet.empty();
        for (int i = 0; i < 6; i++) {
            w = w
                    .add(new PointsCreateDto.Builder()
                            .direction(Direction.DOWN)
                            .point(points.get(i))
                            .size(findShips.get(i).getHealth())
                            .build());
        }
        for (int i = 6; i < findShips.size(); i++) {
            w = w
                    .add(new PointsCreateDto.Builder()
                            .direction(Direction.UP)
                            .point(points.get(i))
                            .size(findShips.get(i).getHealth())
                            .build());
        }
        System.out.println(w);
        Set<Set<String>> pSet = w.map(s -> pointFacade.createPoints(s).get()).map(q -> q.map(CreateDto::getId)).toSet();
        Array<SortedSet<PointDto>> ppp = pSet.map(f -> pointFacade.findAllById(f).get()).toArray();
        System.out.println(ppp);
        Set<ShipPointsDto> toReturn = HashSet.empty();
        for (int i = 0; i < ppp.size(); i++) {
            toReturn = toReturn
                    .add(shipPointsFacade
                            .create(new ShipPointsCreateDto.Builder().ship(findShips.get(i)).points(ppp.get(i)).build())
                            .get());
        }
        return toReturn;
    }
}
