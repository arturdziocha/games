package com.ara.game.usecases.battleship.resources;

import com.ara.game.usecases.battleship.ship.ShipFacade;
import com.google.inject.Guice;
import com.google.inject.Injector;
import external.ConsoleModule;
import io.vavr.collection.Set;


final class ShipsLoader {
    private final ShipFacade shipFacade;
    private final PointsLoader pointsLoader;

    ShipsLoader() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        shipFacade = injector.getInstance(ShipFacade.class);
        pointsLoader = new PointsLoader();
    }

    public Set<String> loadAllShips() {
        Set<String> points = pointsLoader.loadSixStartPoints();
        //TODO
        return null;
    }
}
