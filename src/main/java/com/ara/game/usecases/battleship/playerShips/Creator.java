package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipCreateDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipsDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;

final class Creator {
    private final PlayerShipGateway playerShipGateway;
    private final ShipPointsGateway shipPointsGateway;
    private final Validator validator;
    private final ShipGateway shipGateway;

    public Creator(final PlayerShipGateway playerShipGateway, final ShipGateway shipGateway,
            final ShipPointsGateway shipPointsGateway) {
        this.playerShipGateway = playerShipGateway;
        this.shipGateway = shipGateway;
        this.shipPointsGateway = shipPointsGateway;
        this.validator = new Validator();
    }

    Either<Error, PlayerShipsDto> create(PlayerShipCreateDto inputData) {
        Option<Error> validation = validator.validate(inputData);
        if (validation.isDefined()) {
            return Either.left(validation.get());
        }
        if (isAllShipsPlaced(inputData.getPlayer().getId())) {
            removeShip(inputData.getShip().getId());
            return Either.left(PlayerShipError.ALL_SHIPS_ALREADY_PLACED);
        }
        if (isAlreadyPlaced(inputData)) {
            return Either.left(PlayerShipError.SHIP_ALREADY_PLACED);
        }

        Option<Set<PlayerShipsDto>> placedShips = playerShipGateway.findAllShips(inputData.getPlayer().getId());
        Option<ShipPointsDto> ship = shipPointsGateway.findById(inputData.getShip().getId());
        if (placedShips.isDefined()) {
            Set<ShipPointsDto> alreadyPlacedPoints = placedShips.get().flatMap(s -> s.getShips());
            if (isToCloseTo(alreadyPlacedPoints, ship.get().getPoints())) {
                removeShip(inputData.getShip().getId());
                return Either.left(PlayerShipError.SHIP_IS_TO_CLOSE_OTHER);
            }
        }
        // TODo Finish creating
        return null;
    }

    private boolean isAllShipsPlaced(String playerId) {
        Option<Set<PlayerShipsDto>> alreadyPlaced = playerShipGateway.findAllShips(playerId);
        if (alreadyPlaced.isEmpty()) {
            return false;
        }
        Set<String> alreadyPlacedShortNames = alreadyPlaced
                .get()
                .flatMap(s -> s.getShips())
                .map(r -> r.getShip().getShipClass().getShortName())
                .toSortedSet(String::compareTo);
        Set<String> shipClasses = ShipClass.findAllShortName();
        return shipClasses.containsAll(alreadyPlacedShortNames);
    }

    private boolean isAlreadyPlaced(PlayerShipCreateDto playerShip) {
        return playerShipGateway
                .findByPlayerIdAndShipClassShortName(playerShip.getPlayer().getId(),
                    playerShip.getShip().getShipClass().getShortName())
                .isDefined();
    }

    private void removeShip(String shipId) {
        shipGateway.remove(shipId);
    }

    private boolean isToCloseTo(Set<ShipPointsDto> placedShips, Set<PointDto> shipPoints) {
        Set<PointDto> placed = placedShips.flatMap(ShipPointsDto::getPoints);
        for (PointDto point : placed) {
            for (PointDto shipPoint : shipPoints) {
                if (isNeighbor(point, shipPoint)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNeighbor(PointDto point, PointDto other) {
        return (Math.abs(point.getRow() - other.getRow()) <= 1)
                && (Math.abs(point.getColumn() - other.getColumn()) <= 1);
    }
}
