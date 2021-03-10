package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.domain.Entity;

import io.vavr.control.Option;

final class Game extends Entity {
    private final PlayerDto player;
    private final Option<PlayerDto> secondPlayer;
    private final PlayerDto currentPlayer;

    private Game(Builder builder) {
        super(builder);
        this.player = builder.player;
        this.secondPlayer = builder.secondPlayer;
        this.currentPlayer = builder.currentPlayer;
    }

    PlayerDto getPlayer() {
        return player;
    }

    Option<PlayerDto> getSecondPLayer() {
        return secondPlayer;
    }

    PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    static class Builder extends Entity.Builder<Builder> {
        private PlayerDto player;
        private Option<PlayerDto> secondPlayer;
        private PlayerDto currentPlayer;

        Builder player(final PlayerDto firstPlayer) {
            this.player = firstPlayer;
            return self();
        }

        Builder secondPlayer(final Option<PlayerDto> secondPlayer) {
            this.secondPlayer = secondPlayer;
            return self();
        }

        Builder currentPlayer(final PlayerDto currentPlayer) {
            this.currentPlayer = currentPlayer;
            return self();
        }

        @Override
        protected Game build() {
            return new Game(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

    }

}
