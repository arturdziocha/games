package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipCreateDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Creator {
    private final PlayerShipGateway playerShipGateway;
    private final ShipPointsGateway shipPointsGateway;
    private final Validator validator;
    private final ShipGateway shipGateway;
    private final Mapper mapper;
    private final Logger log;

    public Creator(final PlayerShipGateway playerShipGateway, final ShipGateway shipGateway,
                   final ShipPointsGateway shipPointsGateway) {
        this.playerShipGateway = playerShipGateway;
        this.shipGateway = shipGateway;
        this.shipPointsGateway = shipPointsGateway;
        this.validator = new Validator();
        this.mapper = new Mapper();
        this.log = LoggerFactory.getLogger(Creator.class);
    }

    Either<Error, PlayerShipDto> create(PlayerShipCreateDto inputData) {
        Option<Error> validation = validator.validate(inputData);
        if (validation.isDefined()) {
            return Either.left(validation.get());
        }
        if (isAllShipsPlaced(inputData.getPlayer())) {
            removeShip(inputData.getShip().getId());
            return Either.left(PlayerShipError.ALL_SHIPS_ALREADY_PLACED);
        }
        if (isAlreadyPlaced(inputData)) {
            return Either.left(PlayerShipError.SHIP_ALREADY_PLACED);
        }

        Option<Set<ShipPointsDto>> placedShips = playerShipGateway.findAllShips(inputData.getPlayer().getId());
        Option<ShipPointsDto> ship = shipPointsGateway.findById(inputData.getShip().getId());
        if (placedShips.isDefined()) {
            Set<ShipPointsDto> alreadyPlacedPoints = placedShips.get();
            if (isToCloseTo(alreadyPlacedPoints, ship.get().getPoints())) {
                removeShip(inputData.getShip().getId());
                return Either.left(PlayerShipError.SHIP_IS_TO_CLOSE_OTHER);
            }
        }
        return savePlayerShip(new PlayerShip.Builder().player(inputData.getPlayer()).ship(inputData.getShip()).build());
    }

    private Either<Error, PlayerShipDto> savePlayerShip(PlayerShip playerShip) {
        return Try
                .of(() -> save(playerShip))
                .map(mapper::mapToDto)
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(PlayerShipError.PERSISTENCE_FAILED);
    }

    private PlayerShip save(PlayerShip playerShip) {
        playerShipGateway.save(mapper.mapToDto(playerShip));
        return playerShip;
    }

    boolean isAllShipsPlaced(PlayerDto player) {
        Option<Set<ShipPointsDto>> alreadyPlacedShips = playerShipGateway.findAllShips(player.getId());
        if (alreadyPlacedShips.isEmpty()) {
            return false;
        }
        Set<String> alreadyPlacedShortNames = alreadyPlacedShips
                .get()
                .map(s -> s.getShip().getShipClass().getShortName())
                .toSortedSet(String::compareTo);

        Set<String> shipClasses = ShipClass.findAllShortName();
        return alreadyPlacedShortNames.containsAll(shipClasses);
    }

    private boolean isAlreadyPlaced(PlayerShipCreateDto inputData) {
        return playerShipGateway
                .findByPlayerIdAndShipClassShortName(inputData.getPlayer().getId(),
                        inputData.getShip().getShipClass().getShortName())
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
