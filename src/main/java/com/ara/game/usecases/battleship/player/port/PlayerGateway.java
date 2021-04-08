package com.ara.game.usecases.battleship.player.port;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import io.vavr.control.Option;

public interface PlayerGateway {
    Option<PlayerDto> findById(String id);

    PlayerDto save(PlayerDto inputData);

    Option<PlayerDto> findByName(String name);

    String remove(String id);
}
