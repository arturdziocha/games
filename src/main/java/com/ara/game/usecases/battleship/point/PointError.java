package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.common.Error;

enum PointError implements Error {
    POINT_STRING_CANNOT_BE_EMPTY("Point string cannot be empty"),
    DATA_CANNOT_BE_NULL("Data cannot be null"),
    WRONG_COLUMN_SPECIFIED("Wrong column specified"),
    CANNOT_PARSE_STRING("Point string cannot be parsed"),
    PERSISTENCE_FAILED("SAVING ERROR"),
    ROW_CANNOT_BE_NULL("Row cannot be null"),
    ROW_CANNOT_BE_NEGATIVE("Row cannot be negative"),
    COLUMN_CANNOT_BE_NULL("Column cannot be null"),
    COLUMN_CANNOT_BE_NEGATIVE("Column cannot be negative");

    private final String cause;

    PointError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }

}
