package com.ara.game.usecases.battleship.resources;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.PlayerFacade;
import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import io.vavr.control.Either;

public final class PlayerLoader {
    private final PlayerFacade playerFacade;

    @Inject
    public PlayerLoader(PlayerGateway playerGateway, IdGenerator idGenerator) {
        this.playerFacade = new PlayerFacade(playerGateway, idGenerator);
    }

    public PlayerDto loadFirstPlayer() {
        PlayerCreateDto player = new PlayerCreateDto.Builder()
                .name("Artur")
                .playerType(PlayerType.HUMAN_PLAYER)
                .build();
        Either<Error, CreateDto> playerCreated = playerFacade.create(player);
        return playerFacade.find(playerCreated.get().getId()).get();
    }

    public Set<String> loadTwoPlayers() {
        PlayerCreateDto[] players = {
                new PlayerCreateDto.Builder().name("Artur").playerType(PlayerType.HUMAN_PLAYER).build(),
                new PlayerCreateDto.Builder().name("Jarek").playerType(PlayerType.HUMAN_PLAYER).build()};
        return Stream.of(players).map(player -> playerFacade.create(player).get()).map(CreateDto::getId).toSet();
    }

}
