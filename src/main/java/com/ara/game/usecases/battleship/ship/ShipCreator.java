package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ShipCreator {
    private final ShipGateway shipGateway;
    private final IdGenerator idGenerator;
    private final ShipMapper shipMapper;
    private final Logger log;

    ShipCreator(final ShipGateway shipGateway, final IdGenerator idGenerator) {
        this.shipGateway = shipGateway;
        this.idGenerator = idGenerator;
        this.shipMapper = new ShipMapper();
        this.log = LoggerFactory.getLogger(ShipCreator.class);
    }

    Either<Error, CreateDto> create(final ShipCreateDto shipCreateDto) {
        Option<Error> validation = validate(shipCreateDto);
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

    private Option<Error> validate(final ShipCreateDto shipDto) {
        if (shipDto == null) {
            return Option.some(ShipError.DATA_CANNOT_BE_NULL);
        }
        if (shipDto.getShipClassDto() == null) {
            return Option.some(ShipError.SHIP_CLASS_CANNOT_BE_EMPTY);
        }

        return Option.none();
    }

}
