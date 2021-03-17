package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;

class PlayerValidation {
    private final PlayerGateway playerGateway;

    PlayerValidation(final PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;
    }

    Either<Seq<Error>, PlayerCreateDto> validatePlayer(PlayerCreateDto player) {
        return Validation.combine(validateEmpty(player.getName(), PlayerError.PLAYER_NAME_CANNOT_BE_EMPTY),
                validateName(player.getName(), PlayerError.PLAYER_NAME_ALREADY_EXISTS)).ap((name, playerType) -> player).toEither();
    }

    private Validation<Error, String> validateName(String field, Error error) {
        boolean er = false;
        return playerGateway.findByName(field).isDefined() ? Validation.invalid(error) : Validation.valid(field);
    }

    private Validation<Error, String> validateEmpty(String field, Error error) {
        return Validation.valid(field);
    }

}
