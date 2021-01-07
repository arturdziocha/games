package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.common.Error;

enum PointError implements Error {
    POINT_STRING_CANNOT_BE_EMPTY("Point string cannot be empty"),
    DATA_CANNOT_BE_NULL("Data cannot be null"),
    WRONG_COLUMN_SPECIFIED("Wrong column specified"),
    CANNOT_PARSE_STRING("Point string cannot be parsed");

    private final String cause;

    PointError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }

}
