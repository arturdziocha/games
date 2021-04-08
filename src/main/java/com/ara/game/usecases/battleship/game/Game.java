package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.domain.Entity;
import io.vavr.collection.Set;

final class Game extends Entity {
    private final Set<PlayerDto> players;
    private final PlayerDto currentPlayer;
    private final Integer size;
    private final boolean isStarted;

    private Game(Builder builder) {
        super(builder);
        this.players = builder.players;
        this.size = builder.size;
        this.currentPlayer = builder.currentPlayer;
        this.isStarted = builder.isStarted;
    }

    PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    public Set<PlayerDto> getPlayers() {
        return players;
    }
    public Integer getSize() {
        return size;
    }

    public boolean isStarted() {
        return isStarted;
    }

    static class Builder extends Entity.Builder<Builder> {
        private Set<PlayerDto> players;
        private Integer size;
        private PlayerDto currentPlayer;
        private boolean isStarted;

        Builder players(final Set<PlayerDto> players) {
            this.players = players;
            return self();
        }
        Builder size(final Integer size) {
            this.size = size;
            return self();
        }

        Builder currentPlayer(final PlayerDto currentPlayer) {
            this.currentPlayer = currentPlayer;
            return self();
        }

        Builder isStarted(final boolean isStarted) {
            this.isStarted = isStarted;
            return this;
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
