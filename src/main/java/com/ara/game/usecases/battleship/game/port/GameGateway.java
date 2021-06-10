package com.ara.game.usecases.battleship.game.port;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import io.vavr.control.Option;

public interface GameGateway {
    GameDto save(GameDto game);

    GameDto update(GameDto mapToDto);

    Option<GameDto> find(String gameId);

    String remove(String gameId);

}
