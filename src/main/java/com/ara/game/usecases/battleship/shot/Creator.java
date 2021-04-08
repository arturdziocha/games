package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.enums.PointStatus;
import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.battleship.shot.dto.ShotCreateDto;
import com.ara.game.usecases.battleship.shot.dto.ShotDto;
import com.ara.game.usecases.battleship.shot.port.ShotGateway;
import com.ara.game.usecases.common.Error;
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
    private final Validator validator;
    private final Mapper mapper;
    private final Logger log;

    Creator(final ShotGateway shotGateway, final PlayerShipGateway playerShipGateway, final ShipGateway shipGateway) {
        this.shotGateway = shotGateway;
        this.playerShipGateway = playerShipGateway;
        this.shipGateway = shipGateway;
        this.validator = new Validator();
        this.mapper = new Mapper();
        this.log = LoggerFactory.getLogger(Creator.class);
    }

    Either<Error, ShotDto> create(ShotCreateDto inputData) {
        Option<Error> validated = validator.validate(inputData);
        if (validated.isDefined()) {
            return Either.left(validated.get());
        }
        if (isPointAlreadyShot(inputData.getGame().getCurrentPlayer(), inputData.getPoint())) {
            return Either.left(ShotError.POINT_ALREADY_SHOOTED);
        }

        PlayerDto currentPlayer = inputData.getGame().getCurrentPlayer();
        PlayerDto opponent = calculateOpponent(inputData.getGame());

        Option<ShipWithPointsDto> findShip = findOpponentShip(opponent, inputData.getPoint());
        Shot shot;
        if (findShip.isEmpty()) {
            shot = new Shot.Builder()
                    .player(currentPlayer)
                    .point(inputData.getPoint())
                    .pointStatus(PointStatus.MISS)
                    .shotTime(LocalDateTime.now())
                    .build();
        } else {
            ShipWithPointsDto ship = findShip.get();
            if (ship.getShip().getHealth() > 1) {
                shot = new Shot.Builder()
                        .player(currentPlayer)
                        .point(inputData.getPoint())
                        .pointStatus(PointStatus.HIT)
                        .shotTime(LocalDateTime.now())
                        .build();
            } else {
                ship
                        .getPoints()
                        .filter(point -> !point.equals(inputData.getPoint()))
                        .map(z -> new Shot.Builder()
                                .player(currentPlayer)
                                .point(inputData.getPoint())
                                .pointStatus(PointStatus.SUNK)
                                .shotTime(LocalDateTime.now())
                                .build())
                        .peek(this::updateShot);
                shot = new Shot.Builder()
                        .player(inputData.getGame().getCurrentPlayer())
                        .point(inputData.getPoint())
                        .pointStatus(PointStatus.SUNK)
                        .shotTime(LocalDateTime.now())
                        .build();
                //TODO Set occupied points

            }
            updateHealth(ship.getShip());
        }
        return saveShot(shot);

    }

    private boolean isPointAlreadyShot(PlayerDto player, PointDto point) {
        return shotGateway.findByPointString(player.getId(), point.getPointString()).isDefined();
    }

    private PlayerDto calculateOpponent(GameDto game) {
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
                .update(new ShipDto.Builder()
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
}
