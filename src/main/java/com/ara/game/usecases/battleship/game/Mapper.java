package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;

final class Mapper {
    GameDto mapToDto(Game game) {
        return GameDto.builder()
                .withId(game.getId())
                .withPlayers(game.getPlayers())
                .witSize(game.getSize())
                .withCurrentPlayer(game.getCurrentPlayer())
                .withIsStarted(game.isStarted())
                .withIsFinished(game.isFinished())
                .withCreatedTime(game.getCreatedTime())
                .build();
    }

    Game mapToJoinEntity(GameDto game, PlayerDto player) {
        return Game.builder()
                .id(game.getId())
                .withPlayers(game.getPlayers().add(player))
                .withSize(game.getSize())
                .withCurrentPlayer(game.getCurrentPlayer())
                .withIsStarted(game.isStarted())
                .withIsFinished(game.isFinished())
                .withCreatedTime(game.getCreatedTime())
                .build();
    }
}
