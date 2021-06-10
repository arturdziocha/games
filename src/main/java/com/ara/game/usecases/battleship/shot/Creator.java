package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.enums.PointStatus;
import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.battleship.shot.dto.ShotCreateDto;
import com.ara.game.usecases.battleship.shot.dto.ShotDto;
import com.ara.game.usecases.battleship.shot.port.ShotGateway;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Creator {
    private final ShotGateway shotGateway;
    private final PlayerShipGateway playerShipGateway;
    private final ShipGateway shipGateway;
    private final PointFacade pointFacade;
    private final Validator validator;
    private final Mapper mapper;
    private final Logger log;

    Creator(final ShotGateway shotGateway, final PlayerShipGateway playerShipGateway, final ShipGateway shipGateway,
            final PointGateway pointGateway, final IdGenerator idGenerator) {
        this.shotGateway = shotGateway;
        this.playerShipGateway = playerShipGateway;
        this.shipGateway = shipGateway;
        this.pointFacade = new PointFacade(pointGateway, idGenerator);
        this.validator = new Validator();
        this.mapper = new Mapper();
        this.log = LoggerFactory.getLogger(Creator.class);
    }

    Either<Error, ShotDto> create(ShotCreateDto inputData) {
        Either<Error, ShotCreateDto> validated = validator.validate(inputData);
        if (validated.isLeft()) {
            return Either.left(validated.getLeft());
        }
        if (isPointAlreadyShot(inputData.getGame().getCurrentPlayer(), inputData.getPoint())) {
            return Either.left(ShotError.POINT_ALREADY_SHOOTED);
        }

        PlayerDto currentPlayer = inputData.getGame().getCurrentPlayer();
        PlayerDto opponent = getOpponent(inputData.getGame());

        Option<ShipWithPointsDto> findShip = findOpponentShip(opponent, inputData.getPoint());
        Shot shot;
        if (findShip.isEmpty()) {
            shot = Shot.builder()
                    .withPlayer(currentPlayer)
                    .withPoint(inputData.getPoint())
                    .withPointStatus(PointStatus.MISS)
                    .withShotTime(LocalDateTime.now())
                    .build();
        } else {
            ShipWithPointsDto ship = findShip.get();
            if (ship.getShip().getHealth() > 1) {
                shot = Shot.builder()
                        .withPlayer(currentPlayer)
                        .withPoint(inputData.getPoint())
                        .withPointStatus(PointStatus.HIT)
                        .withShotTime(LocalDateTime.now())
                        .build();
            } else {
                ship
                        .getPoints()
                        .filter(point -> !point.equals(inputData.getPoint()))
                        .map(z -> Shot.builder()
                                .withPlayer(currentPlayer)
                                .withPoint(inputData.getPoint())
                                .withPointStatus(PointStatus.SUNK)
                                .withShotTime(LocalDateTime.now())
                                .build())
                        .peek(this::updateShot);
                shot = Shot.builder()
                        .withPlayer(inputData.getGame().getCurrentPlayer())
                        .withPoint(inputData.getPoint())
                        .withPointStatus(PointStatus.SUNK)
                        .withShotTime(LocalDateTime.now())
                        .build();
                // TODO Set occupied points

            }
            updateHealth(ship.getShip());
        }
        return saveShot(shot);

    }

    private boolean isPointAlreadyShot(PlayerDto player, PointDto point) {
        return shotGateway.findByPointString(player.getId(), point.getPointString()).isDefined();
    }

    private PlayerDto getOpponent(GameDto game) {
        return game.getPlayers().findLast(player -> !player.equals(game.getCurrentPlayer())).get();
    }

    private Option<ShipWithPointsDto> findOpponentShip(PlayerDto opponent, PointDto point) {
        return playerShipGateway.findByPlayerIdAndPointString(opponent.getId(), point.getPointString());
    }

    private Either<Error, ShotDto> updateShot(Shot shot) {
        return Try
                .of(() -> update(shot))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(ShotError.PERSISTENCE_FAILED);
    }

    private ShotDto update(Shot shot) {
        return shotGateway.update(mapper.mapToDto(shot));
    }

    private void updateHealth(ShipDto ship) {
        shipGateway
                .update(ShipDto.builder()
                        .health(ship.getHealth() - 1)
                        .shipClass(ship.getShipClass())
                        .id(ship.getId())
                        .build());
    }

    private Either<Error, ShotDto> saveShot(Shot shot) {
        return Try
                .of(() -> save(shot))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(ShotError.PERSISTENCE_FAILED);
    }

    private ShotDto save(Shot shot) {
        return shotGateway.save(mapper.mapToDto(shot));
    }

    private Set<PointDto> calculateOccupiesPoints(Set<PointDto> shipPoints, Integer boardSize) {
        Set<PointCreateRowColDto> pointsToCreate = HashSet.empty();
        for (PointDto point : shipPoints) {
            for (int i = -1; i <= 1; i++) {
                pointsToCreate = pointsToCreate
                        .add(PointCreateRowColDto.builder()
                                .row(point.getRow() - 1)
                                .column(point.getColumn() + i)
                                .build());
                pointsToCreate = pointsToCreate
                        .add(PointCreateRowColDto.builder()
                                .row(point.getRow() + 1)
                                .column(point.getColumn() + i)
                                .build());
            }
            pointsToCreate = pointsToCreate
                    .add(PointCreateRowColDto.builder().row(point.getRow()).column(point.getColumn() - 1).build());
            pointsToCreate = pointsToCreate
                    .add(PointCreateRowColDto.builder().row(point.getRow()).column(point.getColumn() + 1).build());
        }
        pointsToCreate = pointsToCreate.removeAll(shipPointsToCreateRowCol(shipPoints));
        pointsToCreate = pointsToCreate
                .filter(p -> p.getRow() >= 0 && p.getColumn() >= 0 && p.getRow() < boardSize && p.getColumn() < boardSize);
        Set<PointDto> toReturn = HashSet.empty();
        for (PointCreateRowColDto point : pointsToCreate) {
            Either<Error, PointDto> find = pointFacade.findByRowAndColumn(point.getRow(), point.getColumn());
            if (find.isRight()) {
                toReturn = toReturn.add(find.get());
            } else {
                Either<Error, PointDto> created = pointFacade.create(point);
                toReturn = toReturn.add(created.get());
            }
        }
        return toReturn;
    }

    private Set<PointCreateRowColDto> shipPointsToCreateRowCol(Set<PointDto> points) {
        return points.map(p -> PointCreateRowColDto.builder().row(p.getRow()).column(p.getColumn()).build());
    }
}
