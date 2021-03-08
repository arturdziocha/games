package com.ara.game.usecases.battleship.game;

import com.ara.game.usecases.battleship.game.port.GameGateway;

final class Creator {
    private final GameGateway gameGateway;
    private final Mapper mapper;

    public Creator(final GameGateway gameGateway) {
        this.gameGateway = gameGateway;
        this.mapper = new Mapper();
    }
}
