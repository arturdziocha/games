package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameCreateDto;
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
}
