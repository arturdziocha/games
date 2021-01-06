package com.ara.game.usecases.battleship.shipClass;

import com.ara.game.usecases.common.Error;

enum ShipClassError implements Error {
    SHIP_CLASS_NOT_FOUND("Ship class cannot be find"),
    DATA_CANNOT_BE_EMPTY("Data cannot be empty");
    private final String cause;

    ShipClassError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}