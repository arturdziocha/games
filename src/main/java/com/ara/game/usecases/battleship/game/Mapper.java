package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.CreateDto;

final class Mapper {
    GameDto mapToDto(Game game) {
        return new GameDto.Builder()
                .id(game.getId())
                .players(game.getPlayers())
                .size(game.getSize())
                .currentPlayer(game.getCurrentPlayer())
                .isStarted(game.isStarted())
                .isFinished(game.isFinished())
                .createdTime(game.getCreatedTime())
                .build();
    }

    CreateDto mapToCreateDto(GameDto game) {
        return new CreateDto.Builder().id(game.getId()).build();
    }

    public Game mapToJoinEntity(GameDto game, PlayerDto player) {
        return new Game.Builder()
                .id(game.getId())
                .players(game.getPlayers().add(player))
                .size(game.getSize())
                .currentPlayer(game.getCurrentPlayer())
                .isStarted(game.isStarted())
                .isFinished(game.isFinished())
                .createdTime(game.getCreatedTime())
                .build();
    }
}
