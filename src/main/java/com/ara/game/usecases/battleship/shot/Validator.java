package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.shot.dto.ShotCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import io.vavr.control.Option;

final class Validator {
    Either<Error, ShotCreateDto> validate(ShotCreateDto inputData) {
        if (inputData == null) {
            return Either.left(ShotError.DATA_CANNOT_BE_EMPTY);
        }
        if (!inputData.getGame().isStarted()) {
            return Either.left(ShotError.GAME_NOT_STARTED);
        }
        if (inputData.getPoint() == null) {
            return Either.left(ShotError.POINT_DATA_CANNOT_BE_EMPTY);
        }
        return Either.right(inputData);
    }
}
