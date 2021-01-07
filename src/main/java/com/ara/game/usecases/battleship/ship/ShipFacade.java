package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.control.Either;

public class ShipFacade {

    private final ShipCreator creator;
    private final ShipFinder finder;
    private final ShipRemover remover;

    @Inject
    public ShipFacade(final ShipGateway shipGateway, final IdGenerator idGenerator) {
        this.creator = new ShipCreator(shipGateway, idGenerator);
        this.finder = new ShipFinder(shipGateway);
        this.remover = new ShipRemover(shipGateway);
    }

    public Either<Error, CreateDto> create(final ShipCreateDto inputData) {
        return creator.create(inputData);
    }

    public Either<Error, ShipDto> find(final String id) {
        return finder.find(id);
    }

    public void remove(final String id) {
        remover.remove(id);
    }

}
