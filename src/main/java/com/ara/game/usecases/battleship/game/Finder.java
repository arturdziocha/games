package com.ara.game.usecases.battleship.game;

import org.apache.commons.lang3.StringUtils;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Either;

final class Finder {
    private final GameGateway gameGateway;

    Finder(final GameGateway gameGateway) {
        this.gameGateway = gameGateway;
    }

    Either<Error, GameDto> find(final String id) {
        if (StringUtils.isBlank(id)) {
            return Either.left(GameError.DATA_CANNOT_BE_EMPTY);
        }
        return gameGateway.find(id).toEither(GameError.CANNOT_FIND_GAME);
    }
}
