package com.ara.game.usecases.battleship.playerShips.port;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public interface PlayerShipGateway {

    PlayerShipDto save(PlayerShipDto inputData);

    Option<Set<ShipWithPointsDto>> findAllShips(String playerId);

    Option<ShipWithPointsDto> findByPlayerIdAndShipClassShortName(String playerId, String shipClassShortName);

    Set<String> removeAll(String playerId);

    Option<ShipWithPointsDto> findByPlayerIdAndPointString(String playerId, String pointString);


}
