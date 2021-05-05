package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;

import io.vavr.control.Either;

public class ShipFacade {

    private final Creator creator;
    private final Finder finder;
    private final Remover remover;

    @Inject
    public ShipFacade(final ShipGateway shipGateway, final IdGenerator idGenerator) {
        this.creator = new Creator(shipGateway, idGenerator);
        this.finder = new Finder(shipGateway);
        this.remover = new Remover(shipGateway);
    }

    public Either<Error, ShipDto> create(final ShipCreateDto inputData) {
        return creator.create(inputData);
    }

    public Either<Error, ShipDto> find(final String id) {
        return finder.find(id);
    }

    public void remove(final String id) {
        remover.remove(id);
    }

}
