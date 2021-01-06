package com.ara.game.usecases.battleship.ship.port;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import io.vavr.control.Option;

public interface ShipGateway {
    ShipDto save(ShipDto shipDto);
    Option<ShipDto> findById(String id);

}
