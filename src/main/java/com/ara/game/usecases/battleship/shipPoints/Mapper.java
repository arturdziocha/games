package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;

class Mapper {

    public ShipPointsDto mapToDto(ShipPoints shipPoints) {
        return new ShipPointsDto.Builder().ship(shipPoints.getShip()).points(shipPoints.getPoints()).build();
    }

    public ShipPoints mapToEntity(ShipPointsCreateDto shipPoints) {
        return new ShipPoints.Builder().ship(shipPoints.getShip()).points(shipPoints.getPoints()).build();
    }
}
