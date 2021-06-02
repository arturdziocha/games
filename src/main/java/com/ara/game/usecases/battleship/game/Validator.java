package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameCreateDto;
import com.ara.game.usecases.battleship.game.dto.GameJoinerDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import io.vavr.control.Option;

class Validator {
    Either<Error, GameCreateDto> validateCreate(GameCreateDto inputData) {
        if (inputData == null) {
            return Either.left(GameError.DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getFirstPlayer() == null) {
            return Either.left(GameError.PLAYER_DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getSize() < 8) {
            return Either.left(GameError.TO_SMALL_BOARD_SIZE);
        }
        if (inputData.getSize() > 12) {
            return Either.left(GameError.TO_BIG_BOARD);
        }
        return Either.right(inputData);
    }

    public Either<Error, GameJoinerDto> validateJoin(GameJoinerDto inputData) {
        if (inputData == null) {
            return Either.left(GameError.DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getGame() == null) {
            return Either.left(GameError.GAME_CANNOT_BE_EMPTY);
        }
        if (inputData.getPlayerToJoin() == null) {
            return Either.left(GameError.PLAYER_DATA_CANNOT_BE_EMPTY);
        }
        if (inputData.getGame().getPlayers().contains(inputData.getPlayerToJoin())) {
            return Either.left(GameError.PLAYER_JOINER_HAS_THE_SAME_ID);
        }
        return Either.right(inputData);
    }
}
