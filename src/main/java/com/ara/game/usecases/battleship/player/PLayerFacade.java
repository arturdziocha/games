package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.control.Either;

public class PLayerFacade {
    private final PlayerCreator creator;
    private final PLayerFinder finder;

    @Inject
    public PLayerFacade(final PlayerGateway playerGateway, final IdGenerator idGenerator) {
        this.creator = new PlayerCreator(playerGateway, idGenerator);
        this.finder = new PLayerFinder(playerGateway);
    }

    public Either<Error, CreateDto> create(final PlayerCreateDto inputData) {
        return creator.create(inputData);
    }

    public Either<Error, PlayerDto> find(final String id) {
        return finder.find(id);
    }

    //TODO add remove
}
