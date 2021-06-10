package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.dto.GameJoinerDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Joiner {
    private final GameGateway gameGateway;
    private final Validator validator;
    private final Mapper mapper;
    private final Logger log;

    Joiner(final GameGateway gameGateway) {
        this.gameGateway = gameGateway;
        this.validator = new Validator();
        this.mapper = new Mapper();
        this.log = LoggerFactory.getLogger(Joiner.class);
    }

    Either<Error, GameDto> join(GameJoinerDto inputData) {
        return validator.validateJoin(inputData).flatMap(
                v -> updateGame(mapper.mapToJoinEntity(v.getGame(), v.getPlayerToJoin()))
        );

    }

    private Either<Error, GameDto> updateGame(Game game) {
        return Try
                .of(() -> update(game))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(GameError.PERSISTENCE_FAILED);
    }

    private GameDto update(Game game) {
        return gameGateway.update(mapper.mapToDto(game));
    }
}
