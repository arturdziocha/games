package com.ara.game.usecases.battleship.player.dto;

import com.ara.game.usecases.battleship.enums.PlayerType;

public final class PlayerCreateDto {
    private final String name;
    private final PlayerType playerType;

    private PlayerCreateDto(Builder builder) {
        this.name = builder.name;
        this.playerType = builder.playerType;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private PlayerType playerType;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withPlayerType(final PlayerType playerType) {
            this.playerType = playerType;
            return this;
        }

        public PlayerCreateDto build() {
            return new PlayerCreateDto(this);
        }
    }

}
