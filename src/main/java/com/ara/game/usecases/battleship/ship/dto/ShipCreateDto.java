package com.ara.game.usecases.battleship.ship.dto;

import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;

public class ShipCreateDto {
    private final ShipClassDto shipClassDto;

    public static class Builder {
        private ShipClassDto shipClassDto;

        public Builder shipClassDto(final ShipClassDto shipClassDto) {
            this.shipClassDto = shipClassDto;
            return this;
        }

        public ShipCreateDto build() {
            return new ShipCreateDto(this);
        }
    }

    private ShipCreateDto(Builder builder) {
        this.shipClassDto = builder.shipClassDto;
    }

    public ShipClassDto getShipClassDto() {
        return shipClassDto;
    }
}
