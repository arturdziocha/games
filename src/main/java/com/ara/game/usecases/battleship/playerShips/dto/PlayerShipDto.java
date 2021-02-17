package com.ara.game.usecases.battleship.playerShips.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;

public class PlayerShipDto {

    private final PlayerDto player;
    private final ShipPointsDto ship;

    public static class Builder {
        private PlayerDto player;
        private ShipPointsDto ship;

        public Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public Builder ship(final ShipPointsDto ship) {
            this.ship = ship;
            return this;
        }

        public PlayerShipDto build() {
            return new PlayerShipDto(this);
        }
    }

    private PlayerShipDto(Builder builder) {
        this.player = builder.player;
        this.ship = builder.ship;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public ShipPointsDto getShip() {
        return ship;
    }
}
