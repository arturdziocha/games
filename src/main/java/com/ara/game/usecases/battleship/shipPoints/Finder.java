package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import org.apache.commons.lang3.StringUtils;

class Finder {
    private final ShipPointsGateway shipPointsGateway;

    Finder(final ShipPointsGateway shipPointsGateway) {
        this.shipPointsGateway = shipPointsGateway;
    }

    Either<Error, ShipPointsDto> find(final String shipId) {
        if (StringUtils.isBlank(shipId)) {
            return Either.left(ShipPointsError.SHIP_NOT_SPECIFIED);
        }
        return shipPointsGateway.findById(shipId).toEither(ShipPointsError.CANNOT_FIND_RELATED_POINTS);
    }
}
