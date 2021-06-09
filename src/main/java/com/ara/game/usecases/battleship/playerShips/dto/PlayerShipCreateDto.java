package com.ara.game.usecases.battleship.playerShips.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;

public class PlayerShipCreateDto {
    private final PlayerDto player;
    private final ShipWithPointsDto shipWithPoints;

    private PlayerShipCreateDto(Builder builder) {
        this.player = builder.player;
        this.shipWithPoints = builder.ship;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public ShipWithPointsDto getShipWithPoints() {
        return shipWithPoints;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PlayerDto player;
        private ShipWithPointsDto ship;

        public Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public Builder ship(final ShipWithPointsDto ship) {
            this.ship = ship;
            return this;
        }

        public PlayerShipCreateDto build() {
            return new PlayerShipCreateDto(this);
        }
    }

}
