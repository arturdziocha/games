package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.port.ShipGateway;

final class Remover {
    private final ShipGateway shipGateway;

    Remover(final ShipGateway shipGateway) {
        this.shipGateway = shipGateway;
    }

    void remove(final String shipId) {
        shipGateway.remove(shipId);
    }
}
