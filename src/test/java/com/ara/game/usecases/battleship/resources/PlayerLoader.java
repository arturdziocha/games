package com.ara.game.usecases.battleship.resources;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.PlayerFacade;
import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.common.CreateDto;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

final class PlayerLoader {
    private final PlayerFacade playerFacade;

    PlayerLoader() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        this.playerFacade = injector.getInstance(PlayerFacade.class);
    }

    String loadFirstPlayer() {
        PlayerCreateDto player = new PlayerCreateDto.Builder()
                .name("Artur")
                .playerType(PlayerType.HUMAN_PLAYER)
                .build();
        return playerFacade.create(player).get().getId();
    }

    Set<String> loadTwoPlayers() {
        PlayerCreateDto[] players = {
                new PlayerCreateDto.Builder().name("Artur").playerType(PlayerType.HUMAN_PLAYER).build(),
                new PlayerCreateDto.Builder().name("Artur").playerType(PlayerType.HUMAN_PLAYER).build() };
        return Stream.of(players).map(player -> playerFacade.create(player).get()).map(CreateDto::getId).toSet();
    }

}
