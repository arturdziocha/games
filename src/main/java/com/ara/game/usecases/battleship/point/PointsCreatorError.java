package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.common.Error;

enum PointsCreatorError implements Error {
    ONE_OF_POINTS_CANNOT_BE_CREATED("One of points cannot be created"),
    CANNOT_CREATE_POINTS("Random point cannot be crated");

    private final String cause;

    PointsCreatorError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}
