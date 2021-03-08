package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

public final class GameCreateDto {

    private final PlayerDto firstPlayer;

    private GameCreateDto(Builder builder) {
        this.firstPlayer = builder.firstPlayer;
    }

    public PlayerDto getFirstPlayer() {
        return firstPlayer;
    }

    public static class Builder {
        private PlayerDto firstPlayer;

        public Builder firstPlayer(final PlayerDto firstPlayer) {
            this.firstPlayer = firstPlayer;
            return this;
        }

        public GameCreateDto build() {
            return new GameCreateDto(this);
        }
    }
}
