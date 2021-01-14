package com.ara.game.usecases.battleship.shipPoints.port;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;

public interface ShipPointsGateway {

    ShipPointsDto saveAll(ShipPointsCreateDto shipPoints);

}
