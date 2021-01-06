package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.CreateShipDto;
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


    ShipCreator(ShipGateway shipGateway, IdGenerator idGenerator) {
        this.shipGateway = shipGateway;
        this.idGenerator = idGenerator;
        this.shipMapper = new ShipMapper();
        this.log = LoggerFactory.getLogger(ShipCreator.class);
    }

    Either<Error, CreateDto> create(CreateShipDto createShipDto) {
        Option<Error> validation = validate(createShipDto);
        Ship ship = new Ship.Builder().id(idGenerator.generate()).shipClassDto(createShipDto.getShipClassDto()).build();
        return validation.isDefined() ? Either.left(validation.get()) : saveShip(ship);
    }

    private Either<Error, CreateDto> saveShip(final Ship ship) {
        return Try.of(() -> save(save(ship))).map(shipMapper::mapToCreateDto).onFailure(e -> log.error(e.getMessage())).toEither(ShipError.PERSISTENCE_FAILED);
    }

    private Ship save(final Ship ship) {
        shipGateway.save(shipMapper.mapToDto(ship));
        return ship;
    }

    private Option<Error> validate(final CreateShipDto shipDto) {
        if (shipDto == null) {
            return Option.some(ShipError.DATA_CANNOT_BE_NULL);
        }
        return Option.none();
    }

}
