package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.common.CreateDto;

class Mapper {
    ShipDto mapToDto(final Ship ship) {
        return new ShipDto.Builder()
                .id(ship.getId())
                .shipClass(ship.getShipClass())
                .health(ship.getHealth())
                .build();
    }

    CreateDto mapToCreateDto(final Ship s) {
        return new CreateDto.Builder().id(s.getId()).build();
    }

    Ship mapToEntity(final String id, final ShipClass shipClass) {
        return new Ship.Builder().id(id).shipClass(shipClass).build();
    }

}