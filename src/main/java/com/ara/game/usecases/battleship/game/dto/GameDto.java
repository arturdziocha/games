package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import io.vavr.collection.Set;

import java.time.LocalDateTime;

public final class GameDto {
    private final String id;
    private final Set<PlayerDto> players;
    private final Integer size;
    private final PlayerDto currentPlayer;
    private final boolean isStarted;
    private final boolean isFinished;
    private final LocalDateTime createdTime;

    private GameDto(Builder builder) {
        this.id = builder.id;
        this.players = builder.players;
        this.size = builder.size;
        this.currentPlayer = builder.currentPlayer;
        this.isStarted = builder.isStarted;
        this.isFinished = builder.isFinished;
        this.createdTime = builder.createdTime;
    }

    public String getId() {
        return id;
    }

    public Set<PlayerDto> getPlayers() {
        return players;
    }

    public Integer getSize() {
        return size;
    }

    public PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private Set<PlayerDto> players;
        private Integer size;
        private PlayerDto currentPlayer;
        private boolean isStarted;
        private boolean isFinished;
        private LocalDateTime createdTime;

        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        public Builder withPlayers(final Set<PlayerDto> players) {
            this.players = players;
            return this;
        }

        public Builder witSize(final Integer size) {
            this.size = size;
            return this;
        }

        public Builder withCurrentPlayer(final PlayerDto currentPlayer) {
            this.currentPlayer = currentPlayer;
            return this;
        }

        public Builder withIsStarted(final boolean isStarted) {
            this.isStarted = isStarted;
            return this;
        }

        public Builder withIsFinished(final boolean isFinished) {
            this.isFinished = isFinished;
            return this;
        }

        public Builder withCreatedTime(final LocalDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }

    }

}
