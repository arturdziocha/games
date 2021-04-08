package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.common.Error;

enum GameError implements Error {
    DATA_CANNOT_BE_EMPTY("Data cannot be empty"),
    PLAYER_DATA_CANNOT_BE_EMPTY("Player data cannot be empty"),
    PERSISTENCE_FAILED("Saving error"),
    GAME_CANNOT_BE_EMPTY("Game cannot be empty"),
    CANNOT_FIND_GAME("Cannot find game"),
    PLAYER_JOINER_HAS_THE_SAME_ID("Player to Join cannot have the same id as owner the game"),
    TO_SMALL_BOARD_SIZE("To small board"),
    TO_BIG_BOARD("To big board");
    private final String cause;

    GameError(String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}
