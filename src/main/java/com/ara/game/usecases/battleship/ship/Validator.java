package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Option;

final class Validator {
    Option<Error> validate(final ShipCreateDto shipDto) {
        if (shipDto == null) {
            return Option.some(ShipError.DATA_CANNOT_BE_NULL);
        }
        if (shipDto.getShipClass() == null) {
            return Option.some(ShipError.SHIP_CLASS_CANNOT_BE_EMPTY);
        }

        return Option.none();
    }
}
