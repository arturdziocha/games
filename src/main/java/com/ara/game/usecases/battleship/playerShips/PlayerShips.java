package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;

import io.vavr.collection.Set;

final class PlayerShips {
    private final PlayerDto player;
    private final Set<ShipDto> ships;

    static class Builder {
        private PlayerDto player;
        private Set<ShipDto> ships;

        Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        Builder ships(final Set<ShipDto> ships) {
            this.ships = ships;
            return this;
        }

        PlayerShips build() {
            return new PlayerShips(this);
        }
    }

    public PlayerShips(Builder builder) {
        this.player = builder.player;
        this.ships = builder.ships;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public Set<ShipDto> getShips() {
        return ships;
    }
}
