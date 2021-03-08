package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameDto;

final class Mapper {
    GameDto mapToDto(Game game) {
        return new GameDto.Builder().id(game.getId()).player(game.getPlayer()).opponent(game.getOpponent()).currentPlayer(game.getCurrentPlayer()).build();
    }
}
