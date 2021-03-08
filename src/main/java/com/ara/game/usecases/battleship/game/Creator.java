package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameCreateDto;
import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Creator {
    private final GameGateway gameGateway;
    private final IdGenerator idGenerator;
    private final Validator validator;
    private final Mapper mapper;
    private Logger log;

    public Creator(final GameGateway gameGateway, final IdGenerator idGenerator) {
        this.gameGateway = gameGateway;
        this.idGenerator = idGenerator;
        this.mapper = new Mapper();
        validator = new Validator();
        this.log = LoggerFactory.getLogger(Creator.class);
    }

    Either<Error, GameDto> create(final GameCreateDto inputData) {
        Option<Error> validated = validator.validateCreate(inputData);
        if (validated.isDefined()) {
            return Either.left(validated.get());
        }
        Game game = new Game.Builder()
                .id(idGenerator.generate())
                .player(inputData.getFirstPlayer())
                .opponent(null)
                .currentPlayer(inputData.getFirstPlayer()).build();
        return saveGame(game);
    }

    Either<Error, GameDto> saveGame(Game game) {
        return Try
                .of(() -> save(game))
                .map(mapper::mapToDto)
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(GameError.PERSISTENCE_FAILED);
    }

    private Game save(Game game) {
        gameGateway.save(mapper.mapToDto(game));
        return game;
    }
}
