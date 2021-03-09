package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

public final class GameDto {
    private final String id;
    private final PlayerDto firstPlayer;
    private final PlayerDto secondPlayer;
    private final PlayerDto currentPlayer;

    public GameDto(Builder builder) {
        this.id = builder.id;
        this.firstPlayer = builder.player;
        this.secondPlayer = builder.secondPlayer;
        this.currentPlayer = builder.currentPlayer;

    }

    public String getId() {
        return id;
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

    public static class Builder {
        private String id;
        private PlayerDto player;
        private PlayerDto secondPlayer;
        private PlayerDto currentPlayer;


        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public Builder secondPlayer(final PlayerDto secondPlayer) {
            this.secondPlayer = secondPlayer;
            return this;
        }

        public Builder currentPlayer(final PlayerDto currentPlayer) {
            this.currentPlayer = currentPlayer;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }
    }

}
