package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.shot.port.ShotGateway;

class Creator {
    private final ShotGateway shotGateway;

    Creator(final ShotGateway shotGateway) {
        this.shotGateway = shotGateway;
    }

}
