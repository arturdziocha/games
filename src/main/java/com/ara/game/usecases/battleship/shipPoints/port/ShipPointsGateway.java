package com.ara.game.usecases.battleship.shipPoints.port;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public interface ShipPointsGateway {

    ShipPointsDto save(ShipPointsDto shipPoints);

    Option<ShipPointsDto> findById(String shipId);

    void remove(String shipId);

    Option<Set<ShipPointsDto>> findAllById(Set<String> shipsIds);
}
