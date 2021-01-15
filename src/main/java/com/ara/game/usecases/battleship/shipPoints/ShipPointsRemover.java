package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import org.apache.commons.lang3.StringUtils;

class ShipPointsRemover {
    private final ShipPointsGateway shipPointsGateway;

    ShipPointsRemover(final ShipPointsGateway shipPointsGateway) {
        this.shipPointsGateway = shipPointsGateway;
    }

    final Either<Error, String> remove(final String shipId) {
        if (StringUtils.isEmpty(shipId)) {
            return Either.left(ShipPointsError.SHIP_NOT_SPECIFIED);
        }
        shipPointsGateway.remove(shipId);
        return Either.right("Ship points deleted");
    }

}
