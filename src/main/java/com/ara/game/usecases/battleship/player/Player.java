package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.common.domain.Entity;

final class Player extends Entity {
    private final String name;
    private final PlayerType playerType;

    private Player(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.playerType = builder.playerType;
    }

    String getName() {
        return name;
    }

    PlayerType getPlayerType() {
        return playerType;
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder extends Entity.Builder<Builder> {
        private String name;
        private PlayerType playerType;

        Builder withName(final String name) {
            this.name = name;
            return self();
        }

        Builder withPlayerType(final PlayerType playerType) {
            this.playerType = playerType;
            return self();
        }

        @Override
        protected Player build() {
            return new Player(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

    }
}
