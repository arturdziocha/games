package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.common.Error;

enum ShipPointsError implements Error {
    SHIP_NOT_SPECIFIED("Ship not specified"),
    POINTS_NOT_SPECIFIED("No points specified"),
    PERSISTENCE_FAILED("Persistence failed"),
    CANNOT_FIND_RELATED_POINTS("Cannot find related points");
    private final String cause;

    ShipPointsError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}
