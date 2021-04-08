package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

import io.vavr.collection.Set;

public final class GameDto {
    private final String id;
    private final Set<PlayerDto> players;
    private final Integer size;
    private final PlayerDto currentPlayer;
    private final boolean isStarted;
    private final boolean isFinished;

    private GameDto(Builder builder) {
        this.id = builder.id;
        this.players = builder.players;
        this.size = builder.size;
        this.currentPlayer = builder.currentPlayer;
        this.isStarted = builder.isStarted;
        this.isFinished = builder.isFinished;
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

    public static class Builder {
        private String id;
        private Set<PlayerDto> players;
        private Integer size;
        private PlayerDto currentPlayer;
        private boolean isStarted;
        private boolean isFinished;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder players(final Set<PlayerDto> players) {
            this.players = players;
            return this;
        }

        public Builder size(final Integer size) {
            this.size = size;
            return this;
        }

        public Builder currentPlayer(final PlayerDto currentPlayer) {
            this.currentPlayer = currentPlayer;
            return this;
        }

        public Builder isStarted(final boolean isStarted) {
            this.isStarted = isStarted;
            return this;
        }
        public Builder isFinished(final boolean isFinished) {
            this.isFinished = isFinished;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }

    }

}
