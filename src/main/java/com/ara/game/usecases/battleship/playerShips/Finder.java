package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.common.Error;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.apache.commons.lang3.StringUtils;

final class Finder {
    private final PlayerShipGateway playerShipGateway;

    Finder(final PlayerShipGateway playerShipGateway) {
        this.playerShipGateway = playerShipGateway;
    }

    Either<Error, Set<ShipPointsDto>> findAll(final String playerId) {
        if (StringUtils.isBlank(playerId)) {
            return Either.left(PlayerShipError.PLAYER_NOT_SPECIFIED);
        }
        return playerShipGateway.findAllShips(playerId).toEither(PlayerShipError.NO_SHIPS_DEFINED);
    }
}
