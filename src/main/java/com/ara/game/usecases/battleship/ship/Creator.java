package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Creator {
    private final ShipGateway shipGateway;
    private final IdGenerator idGenerator;
    private final Mapper mapper;
    private final Logger log;
    private final Validator validator;

    Creator(final ShipGateway shipGateway, final IdGenerator idGenerator) {
        this.shipGateway = shipGateway;
        this.idGenerator = idGenerator;
        this.mapper = new Mapper();
        this.log = LoggerFactory.getLogger(Creator.class);
        this.validator = new Validator();
    }

    Either<Error, ShipDto> create(final ShipCreateDto inputData) {
        return validator
                .validate(inputData)
                .flatMap(
                        v -> saveShip(new Ship.Builder().id(idGenerator.generate()).shipClass(v.getShipClass()).build()));
    }

    private Either<Error, ShipDto> saveShip(final Ship ship) {
        return Try
                .of(() -> save(ship))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(ShipError.PERSISTENCE_FAILED);
    }

    private ShipDto save(final Ship ship) {
        return shipGateway.save(mapper.mapToDto(ship));
    }

}
