package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameCreateDto;
import com.ara.game.usecases.battleship.game.dto.GameJoinerDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Option;

class Validator {
    Option<Error> validateCreate(GameCreateDto inputData) {
        if (inputData == null) {
            return Option.some(GameError.DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getFirstPlayer() == null) {
            return Option.some(GameError.PLAYER_DATA_CANNOT_BE_EMPTY);
        }
        return Option.none();
    }

    public Option<Error> validateJoin(GameJoinerDto inputData) {
        if (inputData == null) {
            return Option.some(GameError.DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getGame() == null) {
            return Option.some(GameError.GAME_CANNOT_BE_EMPTY);
        }
        if (inputData.getPlayerToJoin() == null) {
            return Option.some(GameError.PLAYER_DATA_CANNOT_BE_EMPTY);
        }
        if(inputData.getGame().getPlayers().contains(inputData.getPlayerToJoin())) {
            return Option.some(GameError.PLAYER_JOINER_HAS_THE_SAME_ID);
        }
        return Option.none();
    }
}
