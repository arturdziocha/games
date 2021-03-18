package com.ara.game.usecases.battleship.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.dto.GameJoinerDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

final class Joiner {
    private final GameGateway gameGateway;
    private final Validator validator;
    private final Mapper mapper;
    private Logger log;

    Joiner(final GameGateway gameGateway) {
        this.gameGateway = gameGateway;
        this.validator = new Validator();
        this.mapper = new Mapper();
        this.log = LoggerFactory.getLogger(Joiner.class);
    }

    Either<Error, GameDto> join(GameJoinerDto inputData) {
        Option<Error> validated = validator.validateJoin(inputData);
        if (validated.isDefined()) {
            return Either.left(validated.get());
        }
        Game game = new Game.Builder()
                .id(inputData.getGame().getId())
                .player(inputData.getGame().getFirstPlayer())
                .secondPlayer(inputData.getSecondPlayer())
                .currentPlayer(inputData.getGame().getCurrentPlayer())
                .build();
        return updateGame(game);

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
