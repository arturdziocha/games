package com.ara.game.usecases.battleship.shot.port;

import com.ara.game.usecases.battleship.shot.dto.ShotDto;
import io.vavr.collection.Set;

public interface ShotGateway {
    ShotDto save(ShotDto point);

    Set<ShotDto> findAll(String player);

    ShotDto find(String player, String point);

    void remove(String player, String point);
}
