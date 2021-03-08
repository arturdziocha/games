package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.domain.Entity;

final class Game extends Entity {
    private final PlayerDto player;
    private final PlayerDto opponent;
    private final PlayerDto currentPlayer;

    private Game(Builder builder) {
        super(builder);
        this.player = builder.player;
        this.opponent = builder.opponent;
        this.currentPlayer = builder.currentPlayer;
    }

    PlayerDto getPlayer() {
        return player;
    }

    PlayerDto getOpponent() {
        return opponent;
    }

    PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    static class Builder extends Entity.Builder<Builder> {
        private PlayerDto player;
        private PlayerDto opponent;
        private PlayerDto currentPlayer;

        Builder player(final PlayerDto firstPlayer) {
            this.player = firstPlayer;
            return self();
        }

        Builder opponent(final PlayerDto secondPlayer) {
            this.opponent = secondPlayer;
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
