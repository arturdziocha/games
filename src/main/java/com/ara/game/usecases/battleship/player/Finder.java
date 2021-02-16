package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import org.apache.commons.lang3.StringUtils;

class Finder {
    private final PlayerGateway playerGateway;

    Finder(final PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;
    }

    final Either<Error, PlayerDto> find(final String id) {
        if (StringUtils.isEmpty(id)) {
            return Either.left(PlayerError.DATA_CANNOT_BE_EMPTY);
        }
        return playerGateway.findById(id).toEither(PlayerError.CANNOT_FIND_PLAYER);
    }
}
