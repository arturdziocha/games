package com.ara.game.usecases.battleship.shipClass.dto;

import org.apache.commons.lang3.StringUtils;

public class ShipClassDto {
    private final String name;
    private final String shortName;
    private final Integer size;

    public static class Builder {
        private String name;
        private String shortName;
        private Integer size;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder shortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public Builder size(Integer size) {
            this.size = size;
            return this;
        }

        public ShipClassDto build() {
            if (StringUtils.isEmpty(name)) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            if (StringUtils.isEmpty(shortName)) {
                throw new IllegalArgumentException("Short name cannot be mepty");
            }
            if (size < 0 || size > 5) {
                throw new IllegalArgumentException("Size should be between 0 and 6");
            }
            return new ShipClassDto(this);
        }

    }

    private ShipClassDto(Builder builder) {
        this.name = builder.name;
        this.shortName = builder.shortName;
        this.size = builder.size;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Integer getSize() {
        return size;
    }
}
