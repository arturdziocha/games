package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;

class Mapper {
    PlayerShipDto mapToDto(final PlayerShip ship) {
        return new PlayerShipDto.Builder().player(ship.getPlayer()).ship(ship.getShip()).build();
    }
}
