package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;
import com.google.inject.Inject;
import io.vavr.control.Either;

public class ShipPointsFacade {
    private final Creator creator;
    private final Finder finder;
    private final Remover remover;

    @Inject
    public ShipPointsFacade(final ShipPointsGateway shipPointsGateway) {
        this.remover = new Remover(shipPointsGateway);
        this.finder = new Finder(shipPointsGateway);
        this.creator = new Creator(shipPointsGateway);
    }

    public Either<Error, ShipWithPointsDto> create(final ShipPointsCreateDto shipPoints) {
        return creator.createPoints(shipPoints);
    }

    public Either<Error, ShipWithPointsDto> find(final String shipId) {
        return finder.find(shipId);
    }

    public Either<Error, String> remove(final String shipId) {
        return remover.remove(shipId);
    }
}
