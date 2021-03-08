package com.ara.game.usecases.battleship.game.dto;

import java.time.LocalDateTime;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

public final class GameDto {
    private final String id;
    private final PlayerDto firstPlayer;
    private final PlayerDto opponent;
    private final PlayerDto currentPlayer;
    private final LocalDateTime startTime;

    public GameDto(Builder builder) {
        this.id = builder.id;
        this.firstPlayer = builder.firstPlayer;
        this.opponent = builder.opponent;
        this.currentPlayer = builder.currentPlayer;
        this.startTime = builder.startTime;
    }

    public String getId() {
        return id;
    }

    public PlayerDto getFirstPlayer() {
        return firstPlayer;
    }

    public PlayerDto getOpponent() {
        return opponent;
    }

    public PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public static class Builder {
        private String id;
        private PlayerDto firstPlayer;
        private PlayerDto opponent;
        private PlayerDto currentPlayer;
        private LocalDateTime startTime;
        public Builder id(final String id) {
            this.id = id;
            return this;
        }
    }

}
