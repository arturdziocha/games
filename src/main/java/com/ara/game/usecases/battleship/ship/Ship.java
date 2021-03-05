package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.common.domain.Entity;

final class Ship extends Entity {
    private final ShipClass shipClass;
    private final Integer health;

    private Ship(Builder builder) {
        super(builder);
        this.shipClass = builder.shipClass;
        this.health = builder.health;
    }

    ShipClass getShipClass() {
        return shipClass;
    }

    Integer getHealth() {
        return health;
    }

    static class Builder extends Entity.Builder<Builder> {
        private ShipClass shipClass;
        private Integer health;

        Builder shipClass(final ShipClass shipClassDto) {
            this.shipClass = shipClassDto;
            this.health = shipClassDto.getSize();
            return self();
        }

        @Override
        public Ship build() {
            return new Ship(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
