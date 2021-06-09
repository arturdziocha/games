package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;

class Mapper {
    PlayerShipDto mapToDto(final PlayerShip ship) {
        return PlayerShipDto.builder().player(ship.getPlayer()).ship(ship.getShip()).build();
    }
}
