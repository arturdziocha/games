package com.ara.game.usecases.battleship.player.dto;

import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;

public final class PlayerCreateDto {
    private final String name;
    private final PlayerTypeDto playerType;

    public static class Builder {
        private String name;
        private PlayerTypeDto playerType;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder playerType(final PlayerTypeDto playerType) {
            this.playerType = playerType;
            return this;
        }

        public PlayerCreateDto build() {
            return new PlayerCreateDto(this);
        }
    }

    private PlayerCreateDto(Builder builder) {
        this.name = builder.name;
        this.playerType = builder.playerType;
    }

    public String getName() {
        return name;
    }

    public PlayerTypeDto getPlayerType() {
        return playerType;
    }

}
