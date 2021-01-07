package com.ara.game.usecases.battleship.direction;

import com.ara.game.usecases.common.Error;

enum DirectionError implements Error {
    CANNOT_FIND_DIRECTION("Cannot find direction"),
    VALUE_CANNOT_BE_EMPTY("Value cannot be empty");

    private final String cause;

    DirectionError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}