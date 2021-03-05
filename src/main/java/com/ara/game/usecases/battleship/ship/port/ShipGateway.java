package com.ara.game.usecases.battleship.ship.port;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public interface ShipGateway {
    ShipDto save(ShipDto shipDto);

    Option<ShipDto> findById(String id);

    Option<Set<ShipDto>> findAllById(Set<String> shipsId);

    void remove(String id);
}
