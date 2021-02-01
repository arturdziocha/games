package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;

class ShipMapper {

    public ShipPointsDto mapToDto(ShipPoints shipPoints) {
        return new ShipPointsDto.Builder().shipId(shipPoints.getShipId()).points(shipPoints.getPoints()).build();
    }

    public ShipPoints mapToEntity(ShipPointsCreateDto shipPoints) {
        return new ShipPoints.Builder().shipId(shipPoints.getShip().getId()).points(shipPoints.getPoints()).build();
    }
}
