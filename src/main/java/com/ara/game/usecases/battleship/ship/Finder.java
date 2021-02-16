package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import org.apache.commons.lang3.StringUtils;

class Finder {
    private final ShipGateway shipGateway;

    Finder(final ShipGateway shipGateway) {
        this.shipGateway = shipGateway;
    }

    Either<Error, ShipDto> find(final String id) {
        if (StringUtils.isEmpty(id)) {
            return Either.left(ShipError.SHIP_ID_CANNOT_BE_EMPTY);
        }
        return shipGateway.findById(id).toEither(ShipError.SHIP_DOESNT_EXISTS);
    }

}
