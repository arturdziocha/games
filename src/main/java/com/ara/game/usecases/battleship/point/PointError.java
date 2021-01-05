package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.common.Error;

enum PointError implements Error {
    POINT_STRING_CANNOT_BE_EMPTY("Point string cannot be empty");

    private final String cause;

    PointError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }

}
