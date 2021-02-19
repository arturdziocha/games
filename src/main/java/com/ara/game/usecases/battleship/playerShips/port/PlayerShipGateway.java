package com.ara.game.usecases.battleship.playerShips.port;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public interface PlayerShipGateway {

    PlayerShipDto save(PlayerShipDto inputData);

    Option<Set<ShipPointsDto>> findAllShips(String playerId);

    Option<ShipPointsDto> findByPlayerIdAndShipClassShortName(String playerId, String shipClassShortName);

    void remove(String playerId);


}
