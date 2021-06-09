package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;

class Mapper {
    ShipDto mapToDto(final Ship ship) {
        return ShipDto.builder()
                .id(ship.getId())
                .shipClass(ship.getShipClass())
                .health(ship.getHealth())
                .build();
    }
}
