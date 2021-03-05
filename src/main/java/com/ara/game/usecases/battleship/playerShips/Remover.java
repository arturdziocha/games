package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;

final class Remover {
    private final PlayerShipGateway playerShipGateway;

    public Remover(final PlayerShipGateway playerShipGateway) {
        this.playerShipGateway = playerShipGateway;
    }

    void remove(final String playerId) {
        playerShipGateway.remove(playerId);
    }

}
