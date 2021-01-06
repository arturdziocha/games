package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.port.ShipGateway;

final class ShipRemover {
    private final ShipGateway shipGateway;
    ShipRemover(ShipGateway shipGateway) {
        this.shipGateway = shipGateway;
    }

    void remove(String shipId) {
        shipGateway.remove(shipId);
    }
}

