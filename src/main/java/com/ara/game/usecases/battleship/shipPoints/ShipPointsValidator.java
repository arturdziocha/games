package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;

import io.vavr.control.Option;
import com.ara.game.usecases.common.Error;

final class ShipPointsValidator {

    Option<Error> validateAll(final ShipPointsCreateDto shipPoints) {
        if(shipPoints.getShip()==null) {
            return Option.some(ShipPointsError.SHIP_NOT_SPECIFIED);
        }
        if(shipPoints.getPoints().isEmpty()) {
            return Option.some(ShipPointsError.POINTS_NOT_SPECIFIED);
        }
        return Option.none();
    }

}
