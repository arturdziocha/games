package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Option;
import org.apache.commons.lang3.StringUtils;

final class Validator {

    public Option<Error> validate(PlayerCreateDto inputData, PlayerGateway playerGateway) {
        if (inputData == null) {
            return Option.some(PlayerError.DATA_CANNOT_BE_EMPTY);
        }
        if (playerGateway.findByName(inputData.getName()).isDefined()) {
            return Option.some(PlayerError.PLAYER_NAME_ALREADY_EXISTS);
        }
        if (StringUtils.isBlank(inputData.getName())) {
            return Option.some(PlayerError.PLAYER_NAME_CANNOT_BE_EMPTY);
        }
        if (inputData.getPlayerType() == null) {
            return Option.some(PlayerError.PLAYER_TYPE_CANNOT_BE_EMPTY);
        }
        return Option.none();
    }
}
