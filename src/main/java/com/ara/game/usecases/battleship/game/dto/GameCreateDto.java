package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

public final class GameCreateDto {

    private final PlayerDto firstPlayer;
    private final Integer size;

    private GameCreateDto(Builder builder) {
        this.firstPlayer = builder.firstPlayer;
        this.size = builder.size;
    }

    public PlayerDto getFirstPlayer() {
        return firstPlayer;
    }

    public Integer getSize() {
        return size;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PlayerDto firstPlayer;
        private Integer size;

        public Builder withFirstPlayer(final PlayerDto firstPlayer) {
            this.firstPlayer = firstPlayer;
            return this;
        }

        public Builder withSize(final Integer size) {
            this.size = size;
            return this;
        }

        public GameCreateDto build() {
            return new GameCreateDto(this);
        }
    }
}
