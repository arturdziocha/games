package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.shot.dto.ShotCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Option;

final class Validator {
    Option<Error> validate(ShotCreateDto inputData) {
        if (inputData == null) {
            return Option.some(ShotError.DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getPlayer() == null) {
            return Option.some(ShotError.PLAYER_DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getPoint() == null) {
            return Option.some(ShotError.POINT_DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getOpponent() == null) {
            return Option.some(ShotError.OPPONENT_DATA_CANNOT_BE_EMPTY);
        }
        return Option.none();
    }
}
