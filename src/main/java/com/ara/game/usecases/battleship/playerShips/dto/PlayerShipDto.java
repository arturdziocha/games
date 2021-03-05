package com.ara.game.usecases.battleship.playerShips.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;

public final class PlayerShipDto {
    private final PlayerDto player;
    private final ShipDto ship;

    private PlayerShipDto(Builder builder) {
        this.player = builder.player;
        this.ship = builder.ship;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public ShipDto getShip() {
        return ship;
    }

    public static class Builder {
        private PlayerDto player;
        private ShipDto ship;

        public Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public Builder ship(final ShipDto ship) {
            this.ship = ship;
            return this;
        }

        public PlayerShipDto build() {
            return new PlayerShipDto(this);
        }
    }
}
