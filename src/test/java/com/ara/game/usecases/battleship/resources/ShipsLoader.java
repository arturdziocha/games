package com.ara.game.usecases.battleship.resources;

import java.util.stream.IntStream;

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

    public Set<String> loadSix() {
        Set<String> pointsIds = pointsCreator.createSixPoints();
        // System.out.println(pointsIds);
        Array<PointDto> points = pointFacade.findAllById(pointsIds).get().toSortedSet().toArray();
        System.out.println(points);

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
        // TODO create point to do
        Set<Set<CreateDto>> w = HashSet.empty();
        for (int i = 0; i < 6; i++) {
            PointsCreateDto shipPoints = new PointsCreateDto.Builder().direction(Direction.DOWN).point(points.get(i)).size(findShips.get(i).getHealth()).build();
            
            
        }
        System.out.println(findShips);

        PointsCreateDto[] p = {};
        return null;

    }

    public static void main(String[] args) {
        ShipsLoader loader = new ShipsLoader();
        loader.loadSix();

    }
}
