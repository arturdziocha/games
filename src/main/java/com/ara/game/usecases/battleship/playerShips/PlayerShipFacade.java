package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipCreateDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;
import com.google.inject.Inject;
import io.vavr.collection.Set;
import io.vavr.control.Either;

public class PlayerShipFacade {
    private final Creator creator;
    private final Finder finder;
    private final Remover remover;

    @Inject
    public PlayerShipFacade(final PlayerShipGateway playerShipGateway, final ShipGateway shipGateway,
                            final ShipPointsGateway shipPointsGateway) {
        this.creator = new Creator(playerShipGateway, shipGateway, shipPointsGateway);
        this.finder = new Finder(playerShipGateway);
        this.remover = new Remover(playerShipGateway);
    }

    public Either<Error, PlayerShipDto> create(final PlayerShipCreateDto inputData) {
        return creator.create(inputData);
    }

    public Either<Error, Set<ShipPointsDto>> find(final String playerId) {
        return finder.findAll(playerId);
    }

    public void remove(final String playerId) {
        remover.remove(playerId);
    }

    public boolean isAllShipsPlaced(PlayerDto player) {
        return creator.isAllShipsPlaced(player);
    }

}