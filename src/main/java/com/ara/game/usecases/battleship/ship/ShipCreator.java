package com.ara.game.usecases.battleship.ship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

class ShipCreator {
    private final ShipGateway shipGateway;
    private final IdGenerator idGenerator;
    private final ShipMapper shipMapper;
    private final Logger log;
    private final ShipValidator validator;

    ShipCreator(final ShipGateway shipGateway, final IdGenerator idGenerator) {
        this.shipGateway = shipGateway;
        this.idGenerator = idGenerator;
        this.shipMapper = new ShipMapper();
        this.log = LoggerFactory.getLogger(ShipCreator.class);
        this.validator = new ShipValidator();
    }

    Either<Error, CreateDto> create(final ShipCreateDto shipCreateDto) {
        Option<Error> validation = validator.validate(shipCreateDto);
        return validation.isDefined() ? Either.left(validation.get())
                : saveShip(shipMapper.mapToEntity(idGenerator.generate(), shipCreateDto.getShipClassDto()));
    }

    private Either<Error, CreateDto> saveShip(final Ship ship) {
        return Try
                .of(() -> save(ship))
                .map(shipMapper::mapToCreateDto)
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(ShipError.PERSISTENCE_FAILED);
    }

    private Ship save(final Ship ship) {
        shipGateway.save(shipMapper.mapToDto(ship));
        return ship;
    }

}
