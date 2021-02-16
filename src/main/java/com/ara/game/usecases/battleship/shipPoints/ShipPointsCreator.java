package com.ara.game.usecases.battleship.shipPoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;
import com.google.inject.Inject;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

class ShipPointsCreator {
    private final ShipPointsGateway shipPointsGateway;
    private final ShipPointsValidator validator;
    private final ShipMapper mapper;
    private final Logger log;

    @Inject
    public ShipPointsCreator(final ShipPointsGateway shipPointsGateway) {
        this.shipPointsGateway = shipPointsGateway;
        this.validator = new ShipPointsValidator();
        this.mapper = new ShipMapper();
        this.log = LoggerFactory.getLogger(ShipPointsCreator.class);
    }

    Either<Error, ShipPointsDto> createPoints(ShipPointsCreateDto shipPoints) {
        Option<Error> validation = validator.validateAll(shipPoints);

        return validation.isDefined() ? Either.left(validation.get()) : savePoints(mapper.mapToEntity(shipPoints));
    }

    private Either<Error, ShipPointsDto> savePoints(ShipPoints shipPoints) {
        return Try
                .of(() -> save(shipPoints))
                .map(mapper::mapToDto)
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(ShipPointsError.PERSISTENCE_FAILED);
    }

    private ShipPoints save(ShipPoints shipPoints) {
        shipPointsGateway.save(mapper.mapToDto(shipPoints));
        return shipPoints;
    }
}
