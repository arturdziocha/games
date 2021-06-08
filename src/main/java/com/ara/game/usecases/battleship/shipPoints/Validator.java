package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import io.vavr.control.Option;

final class Validator {

    Either<Error, ShipPointsCreateDto> validate(final ShipPointsCreateDto inputData) {
        if (inputData.getShip() == null) {
            return Either.left(ShipPointsError.SHIP_NOT_SPECIFIED);
        }
        if (inputData.getPoints() == null) {
            return Either.left(ShipPointsError.POINTS_NOT_SPECIFIED);
        }
        if (inputData.getPoints().isEmpty()) {
            return Either.left(ShipPointsError.POINTS_NOT_SPECIFIED);
        }

        return Either.right(inputData);
    }

}
