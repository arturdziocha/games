package com.ara.game.usecases.battleship.playerShips;

import io.vavr.collection.Set;

final class PlayerShips {
    private final String playerId;
    private final Set<String> ships;

    static class Builder {
        private String playerId;
        private Set<String> ships;

        Builder playerId(final String playerId) {
            this.playerId = playerId;
            return this;
        }

        Builder ships(final Set<String> ships) {
            this.ships = ships;
            return this;
        }

        PlayerShips build() {
            return new PlayerShips(this);
        }
    }

    public PlayerShips(Builder builder) {
        this.playerId = builder.playerId;
        this.ships = builder.ships;
    }

    public String getPlayerId() {
        return playerId;
    }

    public Set<String> getShips() {
        return ships;
    }
}
