package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.port.PointGateway;

class PointFinder {
    private final PointGateway pointGateway;
    public PointFinder(final PointGateway pointGateway) {
        this.pointGateway = pointGateway;
    }
}
