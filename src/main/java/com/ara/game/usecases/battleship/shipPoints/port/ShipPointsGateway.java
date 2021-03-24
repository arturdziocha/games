package com.ara.game.usecases.battleship.shipPoints.port;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public interface ShipPointsGateway {

    ShipWithPointsDto save(ShipWithPointsDto shipPoints);

    Option<ShipWithPointsDto> findById(String shipId);

    Set<String> remove(String shipId);

    Option<Set<ShipWithPointsDto>> findAllById(Set<String> shipsIds);
}
