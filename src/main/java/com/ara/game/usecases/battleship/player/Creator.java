package com.ara.game.usecases.battleship.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

final class Creator {
    private final PlayerGateway playerGateway;
    private final IdGenerator idGenerator;
    private final Validator validator;
    private final Mapper mapper;
    private final Logger log;

    public Creator(final PlayerGateway playerGateway, final IdGenerator idGenerator) {
        this.playerGateway = playerGateway;
        this.idGenerator = idGenerator;
        this.validator = new Validator();
        this.mapper = new Mapper();
        this.log = LoggerFactory.getLogger(Creator.class);
    }

    Either<Error, CreateDto> create(PlayerCreateDto inputData) {
        Option<Error> validation = validator.validate(inputData);
        if (validation.isDefined()) {
            return Either.left(validation.get());
        }
        return validation.isDefined() ? Either.left(validation.get())
                : savePlayer(mapper.mapToEntity(idGenerator.generate(), inputData));

    }

    Either<Error, CreateDto> savePlayer(final Player player) {
        return Try
                .of(() -> save(player))
                .map(mapper::mapToCreateDto)
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(PlayerError.PERSISTENCE_FAILED);
    }

    private Player save(Player player) {
        playerGateway.save(mapper.mapToDto(player));
        return player;
    }
}