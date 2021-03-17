package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Option;

final class Validator {

    Option<Error> validateAll(final ShipPointsCreateDto inputData) {
        if (inputData.getShip() == null) {
            return Option.some(ShipPointsError.SHIP_NOT_SPECIFIED);
        }
        if (inputData.getPoints().isEmpty()) {
            return Option.some(ShipPointsError.POINTS_NOT_SPECIFIED);
        }
        return Option.none();
    }

}
