package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

class PlayerValidation {
    private final PlayerGateway playerGateway;

    PlayerValidation(final PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;
    }

    Validation<Seq<String>, PlayerCreateDto> validatePlayer(PlayerCreateDto player) {
        return Validation.combine(validateEmpty(player.getName(), PlayerError.PLAYER_NAME_CANNOT_BE_EMPTY),
                validateExists(player.getName(), PlayerError.PLAYER_NAME_ALREADY_EXISTS)).ap((name, playerType)->new PlayerCreateDto.Builder().name(name).playerType(pla));
    }

    private Validation<Error, String> validateExists(String field, Error error) {
        return playerGateway.findByName(field).isDefined() ? Validation.invalid(error) : Validation.valid(field);
    }

    private Validation<Error, String> validateEmpty(String field, Error error) {
        return Validation.valid(field);
    }

}
