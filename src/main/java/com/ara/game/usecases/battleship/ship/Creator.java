package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.control.Either;
import io.vavr.control.Option;
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

    Either<Error, CreateDto> create(final ShipCreateDto shipCreateDto) {
        Option<Error> validation = validator.validate(shipCreateDto);
        return validation.isDefined() ? Either.left(validation.get())
                : saveShip(mapper.mapToEntity(idGenerator.generate(), shipCreateDto.getShipClass()));
    }

    private Either<Error, CreateDto> saveShip(final Ship ship) {
        return Try
                .of(() -> save(ship))
                .map(mapper::mapToCreateDto)
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(ShipError.PERSISTENCE_FAILED);
    }

    private ShipDto save(final Ship ship) {
        return shipGateway.save(mapper.mapToDto(ship));
    }

}
