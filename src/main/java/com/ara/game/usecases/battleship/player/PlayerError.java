package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.common.Error;
enum PlayerError implements Error {
    DATA_CANNOT_BE_EMPTY("Data cannot be empty"),
    PLAYER_NAME_CANNOT_BE_EMPTY("Name cannot be empty"),
    PLAYER_TYPE_CANNOT_BE_EMPTY("Type of player cannot be empty"),
    CANNOT_FIND_PLAYER("Cannot find player id"),
    PERSISTENCE_FAILED("SAVING ERROR");

    private final String cause;

    PlayerError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}