package com.ara.game.usecases.battleship.direction.dto;

public final class DirectionDto {

    private final String name;
    private final String shortName;

    public static class Builder {
        private String name;
        private String shortName;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder shortName(final String shortName) {
            this.shortName = shortName;
            return this;
        }

        public DirectionDto build() {
            return new DirectionDto(this);
        }
    }

    private DirectionDto(Builder builder) {
        this.name = builder.name;
        this.shortName = builder.shortName;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
