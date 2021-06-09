package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import org.apache.commons.lang3.StringUtils;

final class Validator {
    public Either<Error, PlayerCreateDto> validate(PlayerCreateDto inputData) {
        if (inputData == null) {
            return Either.left(PlayerError.DATA_CANNOT_BE_EMPTY);
        }
        if (StringUtils.isBlank(inputData.getName())) {
            return Either.left(PlayerError.PLAYER_NAME_CANNOT_BE_EMPTY);
        }
        if (inputData.getPlayerType() == null) {
            return Either.left(PlayerError.PLAYER_TYPE_CANNOT_BE_EMPTY);
        }
        return Either.right(inputData);
    }
}
