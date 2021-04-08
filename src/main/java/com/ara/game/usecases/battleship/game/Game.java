package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.domain.Entity;
import io.vavr.collection.Set;
import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

final class Game extends Entity {
    private final Set<PlayerDto> players;
    private final PlayerDto currentPlayer;
    private final Integer size;
    private final boolean isStarted;
    private final boolean isFinished;
    private final LocalDateTime startTime;

    private Game(Builder builder) {
        super(builder);
        this.players = builder.players;
        this.currentPlayer = builder.currentPlayer;
        this.size = builder.size;
        this.isStarted = builder.isStarted;
        this.isFinished = builder.isFinished;
        this.startTime = builder.startTime;
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

    public boolean isFinished() {
        return isFinished;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    static class Builder extends Entity.Builder<Builder> {
        private Set<PlayerDto> players;
        private PlayerDto currentPlayer;
        private Integer size;
        private boolean isStarted;
        private boolean isFinished;
        private LocalDateTime startTime;

        Builder players(final Set<PlayerDto> players) {
            this.players = players;
            return self();
        }

        Builder currentPlayer(final PlayerDto currentPlayer) {
            this.currentPlayer = currentPlayer;
            return self();
        }

        Builder size(final Integer size) {
            this.size = size;
            return self();
        }

        Builder isStarted(final boolean isStarted) {
            this.isStarted = isStarted;
            return self();
        }

        Builder isFinished(final boolean isFinished) {
            this.isFinished = isFinished;
            return self();
        }
        Builder startTime(final LocalDateTime startTime){
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
