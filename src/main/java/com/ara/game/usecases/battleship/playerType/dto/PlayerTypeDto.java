package com.ara.game.usecases.battleship.playerType.dto;

public final class PlayerTypeDto {

    private final String id;
    private final String name;

    public static class Builder {
        private String id;
        private String name;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public PlayerTypeDto build() {
            return new PlayerTypeDto(this);
        }
    }

    private PlayerTypeDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
