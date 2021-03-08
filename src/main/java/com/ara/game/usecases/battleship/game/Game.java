package com.ara.game.usecases.battleship.game;

import java.time.LocalDateTime;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.domain.Entity;

final class Game extends Entity {
    private final PlayerDto firstPlayer;
    private final PlayerDto secondPlayer;
    private final PlayerDto currentPlayer;
    private final LocalDateTime startTime;

    private Game(Builder builder) {
        super(builder);
        this.firstPlayer = builder.firstPlayer;
        this.secondPlayer = builder.secondPlayer;
        this.currentPlayer = builder.currentPlayer;
        this.startTime = builder.startTime;
    }

    public PlayerDto getFirstPlayer() {
        return firstPlayer;
    }

    public PlayerDto getSecondPlayer() {
        return secondPlayer;
    }

    public PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    static class Builder extends Entity.Builder<Builder> {
        private PlayerDto firstPlayer;
        private PlayerDto secondPlayer;
        private PlayerDto currentPlayer;
        private LocalDateTime startTime;

        Builder firstPlayer(final PlayerDto firstPlayer) {
            this.firstPlayer = firstPlayer;
            return self();
        }

        Builder secondPlayer(final PlayerDto secondPlayer) {
            this.secondPlayer = secondPlayer;
            return self();
        }

        Builder currentPlayer(final PlayerDto currentPlayer) {
            this.currentPlayer = currentPlayer;
            return self();
        }

        Builder startTime(final LocalDateTime startTime) {
            this.startTime = startTime;
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
