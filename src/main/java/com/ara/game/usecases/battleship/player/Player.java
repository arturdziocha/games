package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;
import com.ara.game.usecases.common.domain.Entity;

final class Player extends Entity {

    private final String name;
    private final PlayerTypeDto playerType;

    static class Builder extends Entity.Builder<Builder> {
        private String name;
        private PlayerTypeDto playerType;

        Builder name(final String name) {
            this.name = name;
            return self();
        }

        Builder playerType(final PlayerTypeDto playerType) {
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

    private Player(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.playerType = builder.playerType;
    }

    String getName() {
        return name;
    }

    PlayerTypeDto getPlayerType() {
        return playerType;
    }
}
