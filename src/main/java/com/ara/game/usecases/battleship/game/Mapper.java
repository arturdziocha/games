package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.common.CreateDto;

final class Mapper {
    GameDto mapToDto(Game game) {
        return new GameDto.Builder()
                .id(game.getId())
                .player(game.getPlayer())
                .secondPlayer(game.getOpponent())
                .currentPlayer(game.getCurrentPlayer())
                .build();
    }

    CreateDto mapToCreateDto(GameDto game) {
        return new CreateDto.Builder().id(game.getId()).build();
    }
}
