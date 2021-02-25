package com.ara.game.usecases.battleship.resources;

import com.ara.game.usecases.battleship.enums.Direction;
import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.battleship.ship.ShipFacade;
import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipPoints.ShipPointsFacade;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.common.CreateDto;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.collection.Array;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

final class ShipsLoader {
    private final ShipFacade shipFacade;
    private final PointsCreator pointsCreator;
    private final ShipPointsFacade shipPointsFacade;
    private final PointFacade pointFacade;

    ShipsLoader() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        shipFacade = injector.getInstance(ShipFacade.class);
        pointsCreator = injector.getInstance(PointsCreator.class);
        pointFacade = injector.getInstance(PointFacade.class);
        shipPointsFacade = injector.getInstance(ShipPointsFacade.class);
    }

    public Set<ShipPointsDto> loadSix() {
        Set<String> pointsIds = pointsCreator.createSixPoints();
        Array<PointDto> points = pointFacade.findAllById(pointsIds).get().toSortedSet().toArray();

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
        Array<Set<PointDto>> ppp = pSet.map(f -> pointFacade.findAllById(f).get()).toArray();
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

    public static void main(String[] args) {
        ShipsLoader loader = new ShipsLoader();
        Set<ShipPointsDto> l = loader.loadSix();
        l.forEach(System.out::println);

    }
}
