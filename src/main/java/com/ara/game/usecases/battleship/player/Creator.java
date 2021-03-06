package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    Either<Error, PlayerDto> create(PlayerCreateDto inputData) {
        Either<Error, PlayerCreateDto> validation = validator.validate(inputData);
        return validation.isLeft() ?
                Either.left(validation.getLeft())
                : playerGateway.findByName(inputData.getName()).isDefined()
                ? Either.left(PlayerError.PLAYER_NAME_ALREADY_EXISTS)
                : savePlayer(mapper.mapToEntity(idGenerator.generate(), inputData));

    }

    Either<Error, PlayerDto> savePlayer(final Player player) {
        return Try
                .of(() -> save(player))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(PlayerError.PERSISTENCE_FAILED);
    }

    private PlayerDto save(Player player) {
        return playerGateway.save(mapper.mapToDto(player));

    }
}
