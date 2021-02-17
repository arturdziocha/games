package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipCreateDto;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Option;

final class Validator {
    Option<Error> validate(PlayerShipCreateDto playerShip){
        if(playerShip == null) {
            return Option.some(PlayerShipError.DATA_NOT_SPECIFIED);
        }
        if(playerShip.getPlayer() == null) {
            return Option.some(PlayerShipError.PLAYER_NOT_SPECIFIED);
        }
        if(playerShip.getShip() == null) {
            return Option.some(PlayerShipError.SHIP_NOT_SPECIFIED);
        }
        return Option.none();
    }
}
