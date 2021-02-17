package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.common.Error;

enum PlayerShipError implements Error {
    DATA_NOT_SPECIFIED("Data cannot be empty"),
    PLAYER_NOT_SPECIFIED("Player not specified"),
    SHIP_NOT_SPECIFIED("Ship not specified"),
    ALL_SHIPS_ALREADY_PLACED("All ships already placed"),
    SHIP_ALREADY_PLACED("Ship is already placed"),
    SHIP_IS_TO_CLOSE_OTHER("Ship is to close other ships");
    private final String cause;

    PlayerShipError(final String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }

}
