package com.ara.game.usecases.battleship.ship.dto;

import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;

public class CreateShipDto {
    private final ShipClassDto shipClassDto;

    private static class Builder {
        private ShipClassDto shipClassDto;

        Builder shipClassDto(final ShipClassDto shipClassDto) {
            this.shipClassDto = shipClassDto;
            return this;
        }

        CreateShipDto build() {
            if (shipClassDto == null) {
                throw new IllegalArgumentException("Ship class cannot be null");
            }
            return new CreateShipDto(this);
        }
    }

    public CreateShipDto(Builder builder) {
        this.shipClassDto = builder.shipClassDto;
    }

    public ShipClassDto getShipClassDto() {
        return shipClassDto;
    }
}
