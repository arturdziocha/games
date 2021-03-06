package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.common.Error;

enum ShotError implements Error {
    DATA_CANNOT_BE_EMPTY("Data cannot be empty"),
    GAME_NOT_STARTED("Game not started"),
    POINT_ALREADY_SHOT("Point is already shot"),
    POINT_DATA_CANNOT_BE_EMPTY("Point data cannot be empty"),
    PERSISTENCE_FAILED("Persistence failed");
    private final String cause;

    ShotError(String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}
