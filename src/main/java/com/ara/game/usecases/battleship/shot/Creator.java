package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.enums.PointStatus;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shot.dto.ShotCreateDto;
import com.ara.game.usecases.battleship.shot.dto.ShotDto;
import com.ara.game.usecases.battleship.shot.port.ShotGateway;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Creator {
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
        if (isAlreadyShot(inputData.getPlayer(), inputData.getPoint())) {
            return Either.left(ShotError.ALREADY_SHOOT);
        }
        Option<ShipPointsDto> findShip = findOpponentShip(inputData.getOpponent(), inputData.getPoint());
        Shot shot;
        if (findShip.isEmpty()) {
            shot = new Shot.Builder().player(inputData.getPlayer()).point(inputData.getPoint()).pointStatus(PointStatus.MISS).build();
        } else {
            ShipPointsDto ship = findShip.get();
            if (ship.getShip().getHealth() > 1) {
                shot =
                        new Shot.Builder().player(inputData.getPlayer()).point(inputData.getPoint()).pointStatus(PointStatus.HIT).build();
            } else {
                ship.getPoints()
                        .filter(p -> !p.equals(inputData.getPoint()))
                        .map(z -> new Shot.Builder().player(inputData.getPlayer()).point(z).pointStatus(PointStatus.SUNK).build()).peek(this::saveShot);
                shot =
                        new Shot.Builder().player(inputData.getPlayer()).point(inputData.getPoint()).pointStatus(PointStatus.SUNK).build();
                //TODO set Occupied points
            }
            updateHealth(ship.getShip());
        }
        return saveShot(shot);
    }

    private boolean isAlreadyShot(PlayerDto player, PointDto point) {
        return shotGateway.findByPointString(player.getId(), point.getPointString()).isDefined();
    }

    private Option<ShipPointsDto> findOpponentShip(PlayerDto opponent, PointDto point) {
        return playerShipGateway.findByPlayerIdAndPointString(opponent.getId(), point.getPointString());
    }

    private void updateHealth(ShipDto ship) {
        shipGateway.update(new ShipDto.Builder().health(ship.getHealth() - 1).shipClass(ship.getShipClass()).id(ship.getId()).build());
    }

    private Either<Error, ShotDto> saveShot(Shot shot) {
        return Try
                .of(() -> save(shot))
                .map(mapper::mapToDto)
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(ShotError.PERSISTENCE_FAILED);
    }

    private Shot save(Shot shot) {
        shotGateway.save(mapper.mapToDto(shot));
        return shot;
    }

}
