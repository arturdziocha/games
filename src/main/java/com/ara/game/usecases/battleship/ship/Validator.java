package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Either;

final class Validator {
    Either<Error, ShipCreateDto> validate(final ShipCreateDto shipDto) {
        if (shipDto == null) {
            return Either.left(ShipError.DATA_CANNOT_BE_NULL);
        }
        if (shipDto.getShipClass() == null) {
            return Either.left(ShipError.SHIP_CLASS_CANNOT_BE_EMPTY);
        }

        return Either.right(shipDto);
    }
}
