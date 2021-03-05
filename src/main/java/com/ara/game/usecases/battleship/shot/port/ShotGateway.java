package com.ara.game.usecases.battleship.shot.port;

import com.ara.game.usecases.battleship.point.dto.PointDto;

public interface ShotGateway {
    PointDto save(PointDto point);

    PointDto find(String player);

    void remove(String player, String point);
}
