package com.ara.game.usecases.battleship.ship.dto;

import com.ara.game.usecases.battleship.enums.ShipClass;

public final class ShipDto {
    private final String id;
    private final ShipClass shipClass;
    private final Integer health;

    public static class Builder {
        private String id;
        private ShipClass shipClass;
        private Integer health;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder shipClass(final ShipClass shipClass) {
            this.shipClass = shipClass;
            return this;
        }

        public Builder health(final Integer health) {
            this.health = health;
            return this;
        }

        public ShipDto build() {
            return new ShipDto(this);
        }
    }

    private ShipDto(Builder builder) {
        this.id = builder.id;
        this.shipClass = builder.shipClass;
        this.health = builder.health;
    }

    public String getId() {
        return id;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    public Integer getHealth() {
        return health;
    }
}
