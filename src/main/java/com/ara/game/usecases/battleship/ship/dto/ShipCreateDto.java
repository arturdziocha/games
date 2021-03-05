package com.ara.game.usecases.battleship.ship.dto;

import com.ara.game.usecases.battleship.enums.ShipClass;

public class ShipCreateDto {
    private final ShipClass shipClass;

    private ShipCreateDto(Builder builder) {
        this.shipClass = builder.shipClass;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    public static class Builder {
        private ShipClass shipClass;

        public Builder shipClass(final ShipClass shipClass) {
            this.shipClass = shipClass;
            return this;
        }

        public ShipCreateDto build() {
            return new ShipCreateDto(this);
        }
    }
}
