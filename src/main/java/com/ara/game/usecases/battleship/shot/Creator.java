package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.shot.dto.ShotCreateDto;
import com.ara.game.usecases.battleship.shot.dto.ShotDto;
import com.ara.game.usecases.battleship.shot.port.ShotGateway;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Either;
import io.vavr.control.Option;

class Creator {
    private final ShotGateway shotGateway;
    private final Validator validator;

    Creator(final ShotGateway shotGateway) {
        this.shotGateway = shotGateway;
        this.validator = new Validator();
    }

    Either<Error, ShotDto> create(ShotCreateDto inputData) {
        Option<Error> validated = validator.validate(inputData);
        if (validated.isDefined()) {
            return Either.left(validated.get());
        }
        if (isAlreadyShooted(inputData.getPlayer(), inputData.getPoint())) {
            return Either.left(ShotError.ALREADY_SHOOT);
        }
        return null;
    }

    private boolean isAlreadyShooted(PlayerDto player, PointDto point) {
        return shotGateway.findByPointString(player.getId(), point.getPointString()).isDefined();
    }

}
