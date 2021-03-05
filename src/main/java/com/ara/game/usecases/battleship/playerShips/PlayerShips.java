package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;

final class PlayerShip {
    private final PlayerDto player;
    private final ShipDto ship;

    public PlayerShip(Builder builder) {
        this.player = builder.player;
        this.ship = builder.ship;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public ShipDto getShip() {
        return ship;
    }

    static class Builder {
        private PlayerDto player;
        private ShipDto ship;

        Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        Builder ship(final ShipDto ship) {
            this.ship = ship;
            return this;
        }

        PlayerShip build() {
            return new PlayerShip(this);
        }
    }
}
