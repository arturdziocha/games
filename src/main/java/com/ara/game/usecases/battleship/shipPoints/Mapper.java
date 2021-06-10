package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;

class Mapper {

    public ShipWithPointsDto mapToDto(ShipPoints shipPoints) {
        return ShipWithPointsDto.builder().withShip(shipPoints.getShip()).withPoints(shipPoints.getPoints()).build();
    }

    public ShipPoints mapToEntity(ShipPointsCreateDto shipPoints) {
        return ShipPoints.builder().withShip(shipPoints.getShip()).withPoints(shipPoints.getPoints()).build();
    }
}
