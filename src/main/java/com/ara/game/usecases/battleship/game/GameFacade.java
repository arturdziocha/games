package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameCreateDto;
import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.dto.GameJoinerDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;

import io.vavr.control.Either;

public class GameFacade {
    private final Creator creator;
    private final Joiner joiner;
    private final Finder finder;

    @Inject
    public GameFacade(final GameGateway gameGateway, final IdGenerator idGenerator) {
        this.creator = new Creator(gameGateway, idGenerator);
        this.joiner = new Joiner(gameGateway);
        this.finder = new Finder(gameGateway);
    }

    Either<Error, CreateDto> create(final GameCreateDto inputData) {
        return creator.create(inputData);
    }

    Either<Error, GameDto> join(final GameJoinerDto inputData) {
        return joiner.join(inputData);
    }

    Either<Error, GameDto> find(final String id) {
        return finder.find(id);
    }
}
