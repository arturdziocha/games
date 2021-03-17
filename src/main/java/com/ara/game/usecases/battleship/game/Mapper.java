package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.dto.GameCreateDto;
import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.common.CreateDto;
import io.vavr.control.Option;

final class Mapper {
    GameDto mapToDto(Game game) {
        return new GameDto.Builder()
                .id(game.getId())
                .player(game.getPlayer())
                .secondPlayer(game.getSecondPLayer())
                .currentPlayer(game.getCurrentPlayer())
                .build();
    }

    CreateDto mapToCreateDto(GameDto game) {
        return new CreateDto.Builder().id(game.getId()).build();
    }

    public Game mapToCreateEntity(String id, GameCreateDto inputData) {
        return new Game.Builder().id(id).player(inputData.getFirstPlayer()).secondPlayer(Option.none()).currentPlayer(inputData.getFirstPlayer()).build();
    }
}
