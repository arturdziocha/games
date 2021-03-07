package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.common.Error;

enum ShotError implements Error {
    DATA_CANNOT_BE_EMPTY("Data cannot be empty"),
    ALREADY_SHOOT("Already shoot"),
    PLAYER_DATA_CANNOT_BE_EMPTY("Player data cannot be empty"),
    POINT_DATA_CANNOT_BE_EMPTY("Point data cannot be empty"),
    OPPONENT_DATA_CANNOT_BE_EMPTY("Opponent data cannot be empty"),
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
