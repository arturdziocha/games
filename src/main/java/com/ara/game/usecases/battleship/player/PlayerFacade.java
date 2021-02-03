package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;

public class PlayerFacade {
    private final PlayerCreator creator;
    private final PlayerFinder finder;
    @Inject
    public PlayerFacade(final PlayerGateway playerGateway, final IdGenerator idGenerator) {
        this.creator = new PlayerCreator(playerGateway, idGenerator);
        this.finder = new PlayerFinder(playerGateway);
    }
    public final Either<Error, CreateDto> create(PlayerCreateDto inputData) {
        return creator.create(inputData);
    }
    public Either<Error, PlayerDto> find(String id) {
        return finder.find(id);
    }
}
