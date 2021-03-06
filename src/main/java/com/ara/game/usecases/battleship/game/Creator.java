package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameCreateDto;
import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.collection.HashSet;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

final class Creator {
    private final GameGateway gameGateway;
    private final IdGenerator idGenerator;
    private final Validator validator;
    private final Mapper mapper;
    private final Logger log;

    Creator(final GameGateway gameGateway, final IdGenerator idGenerator) {
        this.gameGateway = gameGateway;
        this.idGenerator = idGenerator;
        this.mapper = new Mapper();
        validator = new Validator();
        this.log = LoggerFactory.getLogger(Creator.class);
    }

    Either<Error, GameDto> create(final GameCreateDto inputData) {
        return validator.validateCreate(inputData).flatMap(v -> saveGame(Game.builder()
                .id(idGenerator.generate())
                .withCreatedTime(LocalDateTime.now())
                .withIsStarted(false)
                .withIsFinished(false)
                .withCurrentPlayer(v.getFirstPlayer())
                .withSize(v.getSize())
                .withPlayers(HashSet.of(v.getFirstPlayer())).build()));
    }

    private Either<Error, GameDto> saveGame(Game game) {
        return Try
                .of(() -> save(game))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(GameError.PERSISTENCE_FAILED);
    }

    private GameDto save(Game game) {
        return gameGateway.save(mapper.mapToDto(game));
    }

}
