package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.common.Error;

enum ShipError implements Error {
    DATA_CANNOT_BE_NULL("Data cannot be null"),
    SHIP_CLASS_CANNOT_BE_EMPTY("Class of ship cannot be empty"),
    SHIP_ID_CANNOT_BE_EMPTY("Ship id cannot be empty"),
    SHIP_DOESNT_EXISTS("Ship doesn't exists"),
    PERSISTENCE_FAILED("SAVING ERROR");
    private final String cause;

    ShipError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }

}
