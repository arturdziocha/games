package com.ara.game.usecases.battleship.ship.dto;

import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;

public final class ShipDto {
    private final String id;
    private final ShipClassDto shipClassDto;
    private final Integer health;

    public static class Builder {
        private String id;
        private ShipClassDto shipClassDto;
        private Integer health;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder shipClassDto(final ShipClassDto shipClassDto) {
            this.shipClassDto = shipClassDto;
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
        this.shipClassDto = builder.shipClassDto;
        this.health = builder.health;
    }

    public String getId() {
        return id;
    }

    public ShipClassDto getShipClassDto() {
        return shipClassDto;
    }

    public Integer getHealth() {
        return health;
    }
}
