package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.common.Error;

enum ShotError implements Error {
    ALREADY_SHOOT("Already shoot");
    private final String cause;

    ShotError(String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return null;
    }
}
