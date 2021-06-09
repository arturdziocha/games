package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;

class Mapper {

    public ShipWithPointsDto mapToDto(ShipPoints shipPoints) {
        return ShipWithPointsDto.builder().ship(shipPoints.getShip()).points(shipPoints.getPoints()).build();
    }

    public ShipPoints mapToEntity(ShipPointsCreateDto shipPoints) {
        return new ShipPoints.Builder().ship(shipPoints.getShip()).points(shipPoints.getPoints()).build();
    }
}
