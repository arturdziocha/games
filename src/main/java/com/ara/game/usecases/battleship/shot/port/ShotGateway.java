package com.ara.game.usecases.battleship.shot.port;

import com.ara.game.usecases.battleship.shot.dto.ShotDto;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public interface ShotGateway {
    ShotDto save(ShotDto point);

    Set<ShotDto> findAll(String player);

    Option<ShotDto> findByPointId(String player, String pointId);

    Option<ShotDto> findByPointString(String player, String pointString);

    ShotDto remove(String player, String point);

    ShotDto update(ShotDto mapToDto);
}
