package com.ara.game.usecases.battleship.player.dto;

import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;

public final class PlayerDto {
    private final String id;
    private final String name;
    private final PlayerTypeDto playerType;


    public static class Builder {
        private String name;
        private String id;
        private PlayerTypeDto playerType;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder playerType(final PlayerTypeDto playerType) {
            this.playerType = playerType;
            return this;
        }

        public PlayerDto build() {
            return new PlayerDto(this);
        }
    }

    private PlayerDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.playerType = builder.playerType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerTypeDto getPlayerType() {
        return playerType;
    }
}
