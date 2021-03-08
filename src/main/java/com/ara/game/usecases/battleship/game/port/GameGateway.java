package com.ara.game.usecases.battleship.game.port;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import io.vavr.control.Option;

public interface GameGateway {
    GameDto save(GameDto game);

    GameDto join(GameDto game);

    Option<GameDto> find(String gameId);

    void remove(String gameId);

}
