package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;

class Mapper {
    ShipDto mapToDto(final Ship ship) {
        return ShipDto.builder()
                .withId(ship.getId())
                .withShipClass(ship.getShipClass())
                .withHealth(ship.getHealth())
                .build();
    }
}
