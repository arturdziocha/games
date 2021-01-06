package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.common.CreateDto;
import io.vavr.control.Either;

class ShipMapper {
    ShipDto mapToDto(Ship ship) {
        return new ShipDto.Builder().id(ship.getId()).shipClassDto(ship.getShipClassDto()).health(ship.getHealth()).build();
    }

    public CreateDto mapToCreateDto(Ship s) {
        return new CreateDto.Builder().id(s.getId()).build();
    }
}
