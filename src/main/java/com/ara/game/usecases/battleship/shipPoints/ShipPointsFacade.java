package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;
import com.google.inject.Inject;
import io.vavr.control.Either;

public class ShipPointsFacade {
    private final ShipPointsCreator creator;
    private final ShipPointsFinder finder;
    private final ShipPointsRemover remover;

    @Inject
    public ShipPointsFacade(final ShipPointsGateway shipPointsGateway) {
        this.remover = new ShipPointsRemover(shipPointsGateway);
        this.finder = new ShipPointsFinder(shipPointsGateway);
        this.creator = new ShipPointsCreator(shipPointsGateway);
    }

    public Either<Error, ShipPointsDto> create(final ShipPointsCreateDto shipPoints) {
        return creator.createPoints(shipPoints);
    }

    public Either<Error, ShipPointsDto> find(final String shipId) {
        return finder.find(shipId);
    }

    public Either<Error, String> remove(final String shipId) {
        return remover.remove(shipId);
    }
}
