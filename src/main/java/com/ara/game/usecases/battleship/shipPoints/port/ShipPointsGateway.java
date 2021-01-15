package com.ara.game.usecases.battleship.shipPoints.port;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface ShipPointsGateway {

    ShipPointsDto save(ShipPointsDto shipPoints);

    Option<ShipPointsDto> findById(String shipId);

    void remove(String shipId);
}
