package com.ara.game.usecases.battleship.playerType;

import com.ara.game.usecases.common.Error;

enum PlayerTypeError implements Error {
    DATA_CANNOT_BE_EMPTY("Data cannot be empty"),
    CANNOT_FIND_TYPE_OF_PLAYER("Cannot find player type");
    private final String cause;

    PlayerTypeError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}
