package com.ara.game.usecases.battleship.playerShips.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;

import io.vavr.collection.Set;

public final class PlayerShipsDto {
    private final PlayerDto player;
    private final Set<ShipPointsDto> ships;

    public static class Builder {
        private PlayerDto player;
        private Set<ShipPointsDto> ships;

        public Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public Builder ships(final Set<ShipPointsDto> ships) {
            this.ships = ships;
            return this;
        }

        public PlayerShipsDto build() {
            return new PlayerShipsDto(this);
        }
    }

    private PlayerShipsDto(Builder builder) {
        this.player = builder.player;
        this.ships = builder.ships;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public Set<ShipPointsDto> getShips() {
        return ships;
    }
}
