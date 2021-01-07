package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;
import com.ara.game.usecases.common.domain.Entity;

final class Ship extends Entity {
    private final ShipClassDto shipClassDto;
    private final Integer health;

    public static class Builder extends Entity.Builder<Builder> {
        private ShipClassDto shipClassDto;
        private Integer health;

        Builder shipClassDto(final ShipClassDto shipClassDto) {
            this.shipClassDto = shipClassDto;
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

    private Ship(Builder builder) {
        super(builder);
        this.shipClassDto = builder.shipClassDto;
        this.health = builder.health;
    }

    public ShipClassDto getShipClassDto() {
        return shipClassDto;
    }

    public Integer getHealth() {
        return health;
    }

}
