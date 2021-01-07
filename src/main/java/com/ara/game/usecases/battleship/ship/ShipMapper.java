package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;
import com.ara.game.usecases.common.CreateDto;

class ShipMapper {
    ShipDto mapToDto(final Ship ship) {
        return new ShipDto.Builder()
                .id(ship.getId())
                .shipClassDto(ship.getShipClassDto())
                .health(ship.getHealth())
                .build();
    }

    CreateDto mapToCreateDto(final Ship s) {
        return new CreateDto.Builder().id(s.getId()).build();
    }

    Ship mapToEntity(final String id, final ShipClassDto shipClassDto) {
        return new Ship.Builder().id(id).shipClassDto(shipClassDto).build();
    }

}
